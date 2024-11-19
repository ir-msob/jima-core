package ir.msob.jima.core.commons.relatedobject.relatedparty;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.relatedobject.RelatedObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * This class represents a related party with a type, an ID, a role, and a referred type.
 * It implements Comparable interface to provide a natural ordering of its instances.
 */
@Getter
@Setter
@ToString
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedParty extends RelatedObject<String> {
}