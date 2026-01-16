package ir.msob.jima.core.commons.util;

import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.safemodify.UniqueField;
import lombok.SneakyThrows;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Field;

public class DtoUtil {
    private DtoUtil() {
    }

    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, DTO extends BaseDto<ID>> String uniqueField(Class<DTO> dtoClass, DTO dto) {
        Field field = UniqueField.info.getFirstFieldHasAnnotation(dtoClass);
        Assert.notNull(field, "Unique Field Not Found in DTO");
        Object uniqueFieldValue = field.get(dto);
        if (uniqueFieldValue instanceof String uniqueFieldStringValue) {
            return uniqueFieldStringValue;
        }
        throw new CommonRuntimeException("Unique Field type is not String");
    }
}
