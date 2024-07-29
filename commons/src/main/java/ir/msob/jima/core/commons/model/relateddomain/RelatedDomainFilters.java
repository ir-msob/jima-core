package ir.msob.jima.core.commons.model.relateddomain;

import ir.msob.jima.core.commons.model.relatedobject.RelatedObjectFilters;
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
public class RelatedDomainFilters<ID extends Comparable<ID> & Serializable> extends RelatedObjectFilters<ID> {
}