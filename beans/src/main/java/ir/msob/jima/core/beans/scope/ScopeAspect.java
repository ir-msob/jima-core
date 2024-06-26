package ir.msob.jima.core.beans.scope;

import ir.msob.jima.core.commons.exception.resourcenotfound.ResourceNotFoundException;
import ir.msob.jima.core.commons.operation.ConditionalOnOperationUtil;
import ir.msob.jima.core.commons.model.scope.Scope;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
    @Before("@annotation(ir.msob.jima.core.commons.model.scope.Scope)")
    public void beforeScope(JoinPoint joinPoint) {
        // Get the target object and its class
        Object targetObject = joinPoint.getTarget();
        Class<?> resourceClass = targetObject.getClass();

        // Get the method signature and the method
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // Get the @Scope annotation from the method
        Scope scope = method.getAnnotation(Scope.class);

        // Check if the operation specified in the @Scope annotation is present for the resource class
        // If the operation is not present, throw a ResourceNotFoundException
        if (!ConditionalOnOperationUtil.hasOperation(scope.value(), resourceClass))
            throw new ResourceNotFoundException("Unable to find resource", scope.value());
    }
}
