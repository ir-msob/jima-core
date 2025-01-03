package ir.msob.jima.core.commons.childdomain.relatedobject.relatedintegration;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.childdomain.relatedobject.RelatedObjectCriteriaAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * This class represents the filters that can be applied when searching for related integrations.
 * It extends the BaseRelatedFilters class and provides filters for the related integration type, ID, role, and referred type.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedIntegrationCriteriaAbstract<ID extends Comparable<ID> & Serializable, RM extends RelatedIntegrationAbstract<ID>> extends RelatedObjectCriteriaAbstract<ID, String, RM> {
}