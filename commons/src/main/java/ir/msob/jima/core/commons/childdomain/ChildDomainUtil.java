package ir.msob.jima.core.commons.childdomain;

import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteria;
import ir.msob.jima.core.commons.domain.BaseDto;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.SortedSet;
import java.util.function.Function;

public class ChildDomainUtil {


    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseChildDomain<ID>> boolean hasFunction(Class<CD> cdClass, Class<DTO> dtoClass) {
        // Iterate through all declared fields of the current class
        for (var field : dtoClass.getDeclaredFields()) {
            // Check if the field is annotated with ChildDomain
            if (field.isAnnotationPresent(ChildDomain.class)) {
                ChildDomain childDomain = field.getAnnotation(ChildDomain.class);
                // Check if the annotated ChildDomain class matches or is a superclass of the provided class
                if (childDomain.cdClass().isAssignableFrom(cdClass)) {
                   return true;
                }
            }
        }
       return false;
    }

    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseChildDomain<ID>> Function<DTO, SortedSet<CD>> getFunction(Class<CD> cdClass, Class<DTO> dtoClass) {
        // Iterate through all declared fields of the current class
        for (var field : dtoClass.getDeclaredFields()) {
            // Check if the field is annotated with ChildDomain
            if (field.isAnnotationPresent(ChildDomain.class)) {
                ChildDomain childDomain = field.getAnnotation(ChildDomain.class);
                // Check if the annotated ChildDomain class matches or is a superclass of the provided class
                if (childDomain.cdClass().isAssignableFrom(cdClass)) {
                    // Construct the getter method name based on the field name
                    String getterName = "get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);

                    // Retrieve the getter method from the DTO class
                    Method getterMethod;
                    try {
                        getterMethod = dtoClass.getMethod(getterName);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("Getter method not found: " + getterName, e);
                    }

                    // Create and return a Function that invokes the getter method on the DTO
                    return dto -> {
                        try {
                            // Invoke the getter method and cast the result to SortedSet<CD>
                            return (SortedSet<CD>) getterMethod.invoke(dto);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("Error invoking getter method: " + getterName, e);
                        }
                    };
                }
            }
        }
        // Throw an exception if no matching ChildDomain is found
        throw new IllegalArgumentException("No matching ChildDomain found for: " + cdClass);
    }


    // Method to get the criteria class based on childDomain and DTO
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseChildDomain<ID>, CC extends BaseChildCriteria<ID, CD>> Class<CC> getCriteriaClass(Class<CD> cdClass, Class<DTO> dtoClass) {
        // Use reflection to find the ChildDomain annotation
        for (var field : dtoClass.getDeclaredFields()) {
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
