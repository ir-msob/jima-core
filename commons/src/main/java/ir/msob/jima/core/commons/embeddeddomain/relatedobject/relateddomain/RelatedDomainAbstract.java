package ir.msob.jima.core.commons.embeddeddomain.relatedobject.relateddomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.embeddeddomain.relatedobject.BaseRelatedObjectAbstract;
import ir.msob.jima.core.commons.shared.auditinfo.AuditInfo;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * This class represents a related domain with a type, an ID, a role, and a referred type.
 * It implements Comparable interface to provide a natural ordering of its instances.
 *
 * @param <ID> the type of the related domain ID, which must be comparable and serializable
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedDomainAbstract<ID extends Comparable<ID> & Serializable> extends BaseRelatedObjectAbstract<ID, ID> {
    protected RelatedDomainAbstract(ID id, ID parentId, String name, ID relatedId, String role, String referringType, String referringId, String status, Boolean enabled, TimePeriod validFor, AuditInfo auditInfo) {
        super(id, parentId, name, relatedId, role, referringType, referringId, status, enabled, validFor, auditInfo);
    }
}