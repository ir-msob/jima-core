package ir.msob.jima.core.commons.relatedobject.relateddomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.relatedobject.RelatedObjectCriteriaAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * This class represents the filters that can be applied when searching for related domains.
 * It extends the BaseRelatedFilters class and provides filters for the related domain type, ID, role, and referred type.
 *
 * @param <ID> the type of the related domain ID, which must be comparable and serializable
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedDomainCriteriaAbstract<ID extends Comparable<ID> & Serializable> extends RelatedObjectCriteriaAbstract<ID, ID> {
}