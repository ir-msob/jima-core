package ir.msob.jima.core.beans.scope;

import ir.msob.jima.core.commons.resource.Resource;
import ir.msob.jima.core.commons.scope.ResourceDto;
import ir.msob.jima.core.commons.scope.Scope;
import ir.msob.jima.core.commons.scope.ScopeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for scanning and retrieving resources and their associated scopes
 * from the application context.
 */
@RequiredArgsConstructor
public class ScopeScannerService {

    private final ApplicationContext applicationContext;

    /**
     * Retrieves the scopes associated with a given bean.
     *
     * @param bean the bean whose methods are to be scanned for {@link Scope} annotations
     * @return a sorted set of {@link ScopeDto} objects representing the scopes found
     */
    private static SortedSet<ScopeDto> getScopes(Object bean) {
        return Arrays.stream(bean.getClass().getMethods())
                .map(method -> AnnotationUtils.findAnnotation(method, Scope.class))
                .filter(Objects::nonNull)
                .map(ScopeDto::clone)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Retrieves the resource associated with a given bean.
     *
     * @param bean the bean to be scanned for a {@link Resource} annotation
     * @return an {@link Optional} containing the {@link ResourceDto} if found, otherwise empty
     */
    private static Optional<ResourceDto> getResource(Object bean) {
        return Optional.ofNullable(AnnotationUtils.findAnnotation(bean.getClass(), Resource.class))
                .map(ResourceDto::clone);
    }

    /**
     * Retrieves all resources with their associated scopes from the application context.
     *
     * @return a sorted set of {@link ResourceDto} objects representing the resources found
     */
    public SortedSet<ResourceDto> getResources() {
        return applicationContext.getBeansWithAnnotation(Resource.class)
                .values()
                .stream()
                .filter(Objects::nonNull)
                .map(bean -> getResource(bean).map(resource -> {
                    resource.setScopes(getScopes(bean));
                    return resource;
                }))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
