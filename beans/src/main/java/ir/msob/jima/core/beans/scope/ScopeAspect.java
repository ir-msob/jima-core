package ir.msob.jima.core.beans.scope;

import ir.msob.jima.core.beans.properties.JimaProperties;
import ir.msob.jima.core.commons.exception.resourcenotfound.ResourceNotFoundException;
import ir.msob.jima.core.commons.operation.ConditionalOnOperationUtil;
import ir.msob.jima.core.commons.resource.Resource;
import ir.msob.jima.core.commons.scope.Scope;
import ir.msob.jima.core.commons.shared.ResourceType;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * This class is an Aspect in Spring AOP that intercepts methods annotated with @Scope.
 * It verifies the presence of the specified operation in the @Scope annotation for the associated resource class.
 * If the operation is not found, it throws a ResourceNotFoundException.
 *
 * <p>Key functionalities include:</p>
 * <ul>
 *     <li>Validating resource types such as RESTful, gRPC, RSocket, and Kafka.</li>
 *     <li>Intercepting method calls to ensure the required operations are available.</li>
 *     <li>Throwing exceptions when operations are not found, ensuring robust error handling.</li>
 * </ul>
 *
 * @see Scope
 * @see Resource
 * @see ResourceNotFoundException
 * @see ConditionalOnOperationUtil
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ScopeAspect {

    private final JimaProperties jimaProperties;

    private static boolean isValidResourceType(ResourceType resourceType, ResourceType... types) {
        for (ResourceType type : types) {
            if (Objects.equals(resourceType, type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is an advice that runs before any method annotated with @Scope.
     * It checks if the operation specified in the @Scope annotation is present for the resource class.
     * If the operation is not present, it throws a ResourceNotFoundException.
     *
     * @param joinPoint The JoinPoint in AOP, it represents a point in the application where the action took place.
     */
    @Around("@annotation(ir.msob.jima.core.commons.scope.Scope)")
    public Object aroundScope(ProceedingJoinPoint joinPoint) throws Throwable {
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
            if (!ConditionalOnOperationUtil.hasOperation(scope, jimaProperties.getCrud(), resourceClass)) {
                throw new ResourceNotFoundException("Unable to find resource", scope.element() + "/" + scope.operation());
            }
            return joinPoint.proceed();
        } else if (isValidResourceType(resource.type(), ResourceType.KAFKA)) {
            // Check if the operation specified in the @Scope annotation is present for the resource class
            // If the operation is present, allow the method to proceed
            if (ConditionalOnOperationUtil.hasOperation(scope, jimaProperties.getCrud(), resourceClass)) {
                return joinPoint.proceed();
            }
        }
        return null;
    }
}
