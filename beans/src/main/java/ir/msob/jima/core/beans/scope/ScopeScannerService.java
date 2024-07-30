package ir.msob.jima.core.beans.scope;

import ir.msob.jima.core.commons.model.scope.Resource;
import ir.msob.jima.core.commons.model.scope.ResourceDto;
import ir.msob.jima.core.commons.model.scope.Scope;
import ir.msob.jima.core.commons.model.scope.ScopeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ScopeScannerService {

    private final ApplicationContext applicationContext;

    private static SortedSet<ScopeDto> getScopes(Object bean) {
        return Arrays.stream(bean.getClass().getMethods())
                .map(method -> AnnotationUtils.findAnnotation(method, Scope.class))
                .filter(Objects::nonNull)
                .map(ScopeDto::clone)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private static Optional<ResourceDto> getResource(Object bean) {
        return Optional.ofNullable(AnnotationUtils.findAnnotation(bean.getClass(), Resource.class))
                .map(ResourceDto::clone);
    }

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
