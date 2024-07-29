package ir.msob.jima.core.commons.model.relatedparty;

import ir.msob.jima.core.commons.model.relatedobject.RelatedObject;
import lombok.*;

/**
 * This class represents a related party with a type, an ID, a role, and a referred type.
 * It implements Comparable interface to provide a natural ordering of its instances.
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
public class RelatedParty extends RelatedObject<String> {
}