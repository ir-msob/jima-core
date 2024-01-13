package ir.msob.jima.core.commons.model.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.fge.jsonpatch.JsonPatch;
import ir.msob.jima.core.commons.model.criteria.BaseCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonPatchMessage<ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> extends CriteriaMessage<ID, C> {
    private JsonPatch jsonPatch;
}
