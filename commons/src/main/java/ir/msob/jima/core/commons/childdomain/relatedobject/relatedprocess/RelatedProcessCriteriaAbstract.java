package ir.msob.jima.core.commons.childdomain.relatedobject.relatedprocess;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.childdomain.relatedobject.BaseRelatedObjectCriteriaAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * This class represents the filters that can be applied when searching for related processes.
 * It extends the BaseRelatedFilters class and provides filters for the related process type, ID, role, and referred type.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedProcessCriteriaAbstract<ID extends Comparable<ID> & Serializable, RM extends RelatedProcessAbstract<ID>> extends BaseRelatedObjectCriteriaAbstract<ID, String, RM> {
}