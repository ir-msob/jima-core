package ir.msob.jima.core.commons.safemodify;

import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.util.Strings;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.lang.reflect.Field;

public class IdempotencyKeyUtil {
    private IdempotencyKeyUtil() {
    }

    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>> String idempotencyKey(Class<DTO> dtoClass, DTO dto) {
        if (dtoClass == null || dto == null) {
            throw new CommonRuntimeException("DTO class or instance cannot be null");
        }

        Field field = IdempotencyKey.info.getFirstFieldHasAnnotation(dtoClass);

        if (field == null) {
            throw new CommonRuntimeException("@IdempotencyKey not found in {}", dtoClass.getSimpleName());
        }

        Object value = field.get(dto);

        if (value == null) {
            throw new CommonRuntimeException("@IdempotencyKey field '{}' is null", field.getName());
        }

        if (value instanceof String stringValue) {
            if (Strings.isBlank(stringValue)) {
                throw new CommonRuntimeException("@IdempotencyKey field '{}' is blank", field.getName());
            }
            return stringValue;
        }

        throw new CommonRuntimeException(
                "@IdempotencyKey field '{}' must be String (is {})",
                field.getName(),
                field.getType().getSimpleName()
        );
    }
}