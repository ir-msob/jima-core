package ir.msob.jima.core.commons.childdomain.relatedobject.relatedparty;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.childdomain.relatedobject.BaseRelatedObjectAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * This class represents a related party with a type, an ID, a role, and a referred type.
 * It implements Comparable interface to provide a natural ordering of its instances.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedPartyAbstract<ID extends Comparable<ID> & Serializable> extends BaseRelatedObjectAbstract<ID, String> {
}