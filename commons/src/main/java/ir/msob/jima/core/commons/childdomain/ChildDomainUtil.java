package ir.msob.jima.core.commons.childdomain;

import ir.msob.jima.core.commons.childdomain.criteria.BaseChildCriteria;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.util.ReflectionUtil;
import reactor.util.function.Tuple2;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.SortedSet;
import java.util.function.Function;

/**
 * Utility class for handling child domain operations.
 */
public class ChildDomainUtil {
    private ChildDomainUtil() {
    }

    /**
     * Checks if the specified child domain class is assignable from the class specified in the child domain annotation.
     *
     * @param cdClass  The child domain class.
     * @param dtoClass The DTO class.
     * @param <ID>     The type of the ID.
     * @param <DTO>    The type of the DTO.
     * @param <CD>     The type of the child domain.
     * @return True if the child domain class is assignable, false otherwise.
     */
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseChildDomain<ID>> boolean hasFunction(Class<CD> cdClass, Class<DTO> dtoClass) {
        ChildDomain childDomain = ChildDomain.info.getFirstAnnotation(dtoClass);
        return childDomain != null && childDomain.cdClass().isAssignableFrom(cdClass);
    }

    /**
     * Retrieves a function that gets the sorted set of child domains from the DTO.
     *
     * @param cdClass  The child domain class.
     * @param dtoClass The DTO class.
     * @param <ID>     The type of the ID.
     * @param <DTO>    The type of the DTO.
     * @param <CD>     The type of the child domain.
     * @return A function that gets the sorted set of child domains from the DTO.
     */
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseChildDomain<ID>> Function<DTO, SortedSet<CD>> getFunction(Class<CD> cdClass, Class<DTO> dtoClass) {
        for (Tuple2<Method, ChildDomain> tuple2 : ChildDomain.info.getGetterMethodHasAnnotation(dtoClass)) {
            if (tuple2.getT2().cdClass().isAssignableFrom(cdClass)) {
                return dto -> {
                    try {
                        Object value = tuple2.getT1().invoke(dto);
                        if (value instanceof SortedSet<?> objects) {
                            return (SortedSet<CD>) objects;
                        } else {
                            throw new CommonRuntimeException(
                                    "Expected SortedSet<" + cdClass.getSimpleName() + "> but got " +
                                            (value == null ? "null" : value.getClass().getName())
                            );
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new CommonRuntimeException("Error invoking getter method: " + tuple2.getT1().getName(), e);
                    }
                };
            }
        }
        throw new IllegalArgumentException("No matching ChildDomain found for: " + cdClass);
    }

    /**
     * Retrieves the criteria class for the specified child domain class and DTO class.
     *
     * @param cdClass  The child domain class.
     * @param dtoClass The DTO class.
     * @param <ID>     The type of the ID.
     * @param <DTO>    The type of the DTO.
     * @param <CD>     The type of the child domain.
     * @param <CC>     The type of the child criteria.
     * @return The criteria class for the specified child domain class and DTO class.
     */
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseChildDomain<ID>, CC extends BaseChildCriteria<ID, CD>> Class<CC> getCriteriaClass(Class<CD> cdClass, Class<DTO> dtoClass) {
        for (var field : ReflectionUtil.getFields(dtoClass)) {
            ChildDomain childDomain = field.getAnnotation(ChildDomain.class);
            if (childDomain != null && childDomain.cdClass().isAssignableFrom(cdClass)) {
                return (Class<CC>) childDomain.ccClass();
            }
        }
        throw new IllegalArgumentException("No matching criteria class found for ChildDomain: " + cdClass);
    }
}