package ir.msob.jima.core.commons.embeddeddomain;

import ir.msob.jima.core.commons.embeddeddomain.criteria.BaseEmbeddedCriteria;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.util.ReflectionUtil;
import org.jspecify.annotations.NonNull;
import reactor.util.function.Tuple2;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.SortedSet;
import java.util.function.Function;

/**
 * Utility class for handling embedded domain operations.
 */
public class EmbeddedDomainUtil {
    private EmbeddedDomainUtil() {
    }

    /**
     * Checks if the specified embedded domain class is assignable from the class specified in the embedded domain annotation.
     *
     * @param cdClass  The embedded domain class.
     * @param dtoClass The DTO class.
     * @param <ID>     The type of the ID.
     * @param <DTO>    The type of the DTO.
     * @param <CD>     The type of the embedded domain.
     * @return True if the embedded domain class is assignable, false otherwise.
     */
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseEmbeddedDomain<ID>> boolean hasFunction(Class<CD> cdClass, Class<DTO> dtoClass) {
        EmbeddedDomain embeddedDomain = EmbeddedDomain.info.getFirstAnnotation(dtoClass);
        return embeddedDomain != null && embeddedDomain.cdClass().isAssignableFrom(cdClass);
    }

    /**
     * Retrieves a function that gets the sorted set of embedded domains from the DTO.
     *
     * @param cdClass  The embedded domain class.
     * @param dtoClass The DTO class.
     * @param <ID>     The type of the ID.
     * @param <DTO>    The type of the DTO.
     * @param <CD>     The type of the embedded domain.
     * @return A function that gets the sorted set of embedded domains from the DTO.
     */
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseEmbeddedDomain<ID>> Function<DTO, SortedSet<CD>> getFunction(Class<CD> cdClass, Class<DTO> dtoClass) {
        for (Tuple2<@NonNull Method, @NonNull EmbeddedDomain> tuple2 : EmbeddedDomain.info.getGetterMethodHasAnnotation(dtoClass)) {
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
        throw new IllegalArgumentException("No matching embeddeddomain found for: " + cdClass);
    }

    /**
     * Retrieves the criteria class for the specified embedded domain class and DTO class.
     *
     * @param cdClass  The embedded domain class.
     * @param dtoClass The DTO class.
     * @param <ID>     The type of the ID.
     * @param <DTO>    The type of the DTO.
     * @param <CD>     The type of the embedded domain.
     * @param <CC>     The type of the embedded criteria.
     * @return The criteria class for the specified embedded domain class and DTO class.
     */
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>, CD extends BaseEmbeddedDomain<ID>, CC extends BaseEmbeddedCriteria<ID, CD>> Class<CC> getCriteriaClass(Class<CD> cdClass, Class<DTO> dtoClass) {
        for (var field : ReflectionUtil.getFields(dtoClass)) {
            EmbeddedDomain embeddedDomain = field.getAnnotation(EmbeddedDomain.class);
            if (embeddedDomain != null && embeddedDomain.cdClass().isAssignableFrom(cdClass)) {
                return (Class<CC>) embeddedDomain.ccClass();
            }
        }
        throw new IllegalArgumentException("No matching criteria class found for embeddeddomain: " + cdClass);
    }
}