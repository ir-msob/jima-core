package ir.msob.jima.core.beans.scope;

import ir.msob.jima.core.commons.model.operation.ConditionalOnOperationUtil;
import ir.msob.jima.core.commons.model.scope.Scope;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * This class is an Aspect in Spring AOP that handles methods annotated with @ScopeInitializer.
 * It checks if the operation specified in the @Scope annotation is present for the resource class.
 * If the operation is present, it allows the method to proceed.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ScopeInitializerAspect {

    /**
     * This method is an advice that runs around any method annotated with @ScopeInitializer.
     * It checks if the operation specified in the @Scope annotation is present for the resource class.
     * If the operation is present, it allows the method to proceed.
     *
     * @param joinPoint The JoinPoint in AOP, it represents a point in the application where the action took place.
     */
    @Around("@annotation(ir.msob.jima.core.commons.model.scope.ScopeInitializer)")
    public void aroundScopeInitializer(ProceedingJoinPoint joinPoint) throws Throwable {
        // Get the target object and its class
        Object targetObject = joinPoint.getTarget();
        Class<?> resourceClass = targetObject.getClass();

        // Get the method signature and the method
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // Get the @Scope annotation from the method
        Scope scope = method.getAnnotation(Scope.class);

        // Check if the operation specified in the @Scope annotation is present for the resource class
        // If the operation is present, allow the method to proceed
        if (ConditionalOnOperationUtil.hasOperation(scope.value(), resourceClass))
            joinPoint.proceed();
    }
}