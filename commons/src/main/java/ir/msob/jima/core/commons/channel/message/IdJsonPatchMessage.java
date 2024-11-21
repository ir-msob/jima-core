package ir.msob.jima.core.commons.channel.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.fge.jsonpatch.JsonPatch;
import ir.msob.jima.core.commons.dto.ModelType;
import lombok.*;

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
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdJsonPatchMessage<ID extends Comparable<ID> & Serializable> extends ModelType {
    /**
     * The ID of the message.
     */
    private ID id;
    /**
     * The 'jsonPatch' field holds the JSON Patch that is part of the message.
     * This is used when the message needs to carry modifications to a JSON document.
     */
    private JsonPatch jsonPatch;
}