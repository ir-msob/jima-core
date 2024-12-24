package ir.msob.jima.core.commons.childdomain;

import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteria;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.util.ReflectionUtil;
import reactor.util.function.Tuple2;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.SortedSet;
import java.util.function.Function;

public class ChildDomainUtil {


    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseChildDomain<ID>> boolean hasFunction(Class<CD> cdClass, Class<DTO> dtoClass) {
        ChildDomain childDomain = ChildDomain.info.getFirstAnnotation(dtoClass);
        return childDomain != null && childDomain.cdClass().isAssignableFrom(cdClass);
    }

    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseChildDomain<ID>> Function<DTO, SortedSet<CD>> getFunction(Class<CD> cdClass, Class<DTO> dtoClass) {
        for (Tuple2<Method, ChildDomain> tuple2 : ChildDomain.info.getGetterMethodHasAnnotation(dtoClass)) {
            // Check if the annotated ChildDomain class matches or is a superclass of the provided class
            if (tuple2.getT2().cdClass().isAssignableFrom(cdClass)) {

                // Create and return a Function that invokes the getter method on the DTO
                return dto -> {
                    try {
                        // Invoke the getter method and cast the result to SortedSet<CD>
                        return (SortedSet<CD>) tuple2.getT1().invoke(dto);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("Error invoking getter method: " + tuple2.getT1().getName(), e);
                    }
                };
            }
        }
        // Throw an exception if no matching ChildDomain is found
        throw new IllegalArgumentException("No matching ChildDomain found for: " + cdClass);
    }


    // Method to get the criteria class based on childDomain and DTO
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseChildDomain<ID>, CC extends BaseChildCriteria<ID, CD>> Class<CC> getCriteriaClass(Class<CD> cdClass, Class<DTO> dtoClass) {
        // Use reflection to find the ChildDomain annotation
        for (var field : ReflectionUtil.getFields(dtoClass)) {
            if (field.isAnnotationPresent(ChildDomain.class)) {
                ChildDomain childDomain = field.getAnnotation(ChildDomain.class);
                // Check if the annotated ChildDomain class matches or is a superclass of the provided class
                if (childDomain.cdClass().isAssignableFrom(cdClass)) {
                    // Return the criteria class from the annotation
                    return (Class<CC>) childDomain.ccClass();
                }
            }
        }
        // Throw an exception if no matching ChildDomain is found
        throw new IllegalArgumentException("No matching criteria class found for ChildDomain: " + cdClass);
    }

}
