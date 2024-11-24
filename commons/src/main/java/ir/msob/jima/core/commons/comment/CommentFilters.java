package ir.msob.jima.core.commons.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.relatedobject.RelatedObjectCriteriaAbstract;
import ir.msob.jima.core.commons.relatedobject.relatedparty.RelatedPartyCriteriaAbstract;
import ir.msob.jima.core.commons.shared.audit.auditinfo.AuditInfoFilters;
import ir.msob.jima.core.commons.shared.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.shared.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentFilters<ID extends Comparable<ID> & Serializable> implements BaseFilters {
    private Filter<String> comment;
    private RelatedPartyCriteriaAbstract relatedParty;
    private AuditInfoFilters auditInfo;
    private RelatedObjectCriteriaAbstract<ID> relatedObject;
}