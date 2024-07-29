package ir.msob.jima.core.commons.model.relateddomain;

import ir.msob.jima.core.commons.model.relatedobject.RelatedObject;
import lombok.*;

import java.io.Serializable;

/**
 * This class represents a related domain with a type, an ID, a role, and a referred type.
 * It implements Comparable interface to provide a natural ordering of its instances.
 *
 * @param <ID> the type of the related domain ID, which must be comparable and serializable
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
public class RelatedDomain<ID extends Comparable<ID> & Serializable> extends RelatedObject<ID> {
}