package ir.msob.jima.core.commons.relatedobject.relateddomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.relatedobject.RelatedObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedDomain<ID extends Comparable<ID> & Serializable> extends RelatedObject<ID> {
}