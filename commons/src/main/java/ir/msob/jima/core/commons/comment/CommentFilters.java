package ir.msob.jima.core.commons.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.relatedobject.RelatedObjectFilters;
import ir.msob.jima.core.commons.relatedobject.relatedparty.RelatedPartyFilters;
import ir.msob.jima.core.commons.shared.auditinfo.AuditInfoFilters;
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
    private RelatedPartyFilters relatedParty;
    private AuditInfoFilters auditInfo;
    private RelatedObjectFilters<ID> relatedObject;
}