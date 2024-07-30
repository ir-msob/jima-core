package ir.msob.jima.core.beans.scope;

import ir.msob.jima.core.commons.exception.resourcenotfound.ResourceNotFoundException;
import ir.msob.jima.core.commons.model.scope.Resource;
import ir.msob.jima.core.commons.model.ResourceType;
import ir.msob.jima.core.commons.model.scope.Scope;
import ir.msob.jima.core.commons.operation.ConditionalOnOperationUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * This class is an Aspect in Spring AOP that handles methods annotated with @Scope.
 * It checks if the operation specified in the @Scope annotation is present for the resource class.
 * If the operation is not present, it throws a ResourceNotFoundException.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ScopeAspect {

    /**
     * This method is an advice that runs before any method annotated with @Scope.
     * It checks if the operation specified in the @Scope annotation is present for the resource class.
     * If the operation is not present, it throws a ResourceNotFoundException.
     *
     * @param joinPoint The JoinPoint in AOP, it represents a point in the application where the action took place.
     */
    @Around("@annotation(ir.msob.jima.core.commons.model.scope.Scope)")
    public void aroundScope(ProceedingJoinPoint joinPoint) throws Throwable {
        // Get the target object and its class
        Object targetObject = joinPoint.getTarget();
        Class<?> resourceClass = targetObject.getClass();
        Resource resource = resourceClass.getAnnotation(Resource.class);

        // Get the method signature and the method
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // Get the @Scope annotation from the method
        Scope scope = method.getAnnotation(Scope.class);

        if (isValidResourceType(resource.type(), ResourceType.RESTFUL, ResourceType.GRPC, ResourceType.RSOCKET)) {
            // Check if the operation specified in the @Scope annotation is present for the resource class
            // If the operation is not present, throw a ResourceNotFoundException
            if (!ConditionalOnOperationUtil.hasOperation(scope.value(), resourceClass))
                throw new ResourceNotFoundException("Unable to find resource", scope.value());
        } else if (isValidResourceType(resource.type(), ResourceType.KAFKA)) {
            // Check if the operation specified in the @Scope annotation is present for the resource class
            // If the operation is present, allow the method to proceed
            if (ConditionalOnOperationUtil.hasOperation(scope.value(), resourceClass))
                joinPoint.proceed();
        }
    }

    private static boolean isValidResourceType(ResourceType resourceType, ResourceType... types) {
        for (ResourceType type : types) {
            if (Objects.equals(resourceType, type)) {
                return true;
            }
        }
        return false;
    }
}
