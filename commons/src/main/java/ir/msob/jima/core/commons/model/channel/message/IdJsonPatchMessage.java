package ir.msob.jima.core.commons.model.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.fge.jsonpatch.JsonPatch;
import ir.msob.jima.core.commons.model.criteria.BaseCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * The 'IdJsonPatchMessage' class is a specialized form of 'IdMessage' that includes a 'JsonPatch'.
 * This class is used when a message needs to carry a JSON Patch along with the ID.
 * It is parameterized with a type 'ID' that extends 'Comparable' and 'Serializable'.
 *
 * @param <ID> The type of ID.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdJsonPatchMessage<ID extends Comparable<ID> & Serializable> extends IdMessage<ID> {
    /**
     * The 'jsonPatch' field holds the JSON Patch that is part of the message.
     * This is used when the message needs to carry modifications to a JSON document.
     */
    private JsonPatch jsonPatch;
}