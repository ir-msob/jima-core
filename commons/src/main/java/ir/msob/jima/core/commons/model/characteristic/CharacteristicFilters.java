package ir.msob.jima.core.commons.model.characteristic;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.DataType;
import ir.msob.jima.core.commons.model.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacteristicFilters implements BaseFilters {
    private Filter<String> key;
    private Filter<Serializable> value;
    private Filter<DataType> dataType;
    private Filter<Boolean> isArray;
}
