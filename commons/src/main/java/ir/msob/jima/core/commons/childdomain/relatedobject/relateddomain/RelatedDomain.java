package ir.msob.jima.core.commons.childdomain.relatedobject.relateddomain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.shared.auditinfo.AuditInfo;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriod;
import lombok.*;

import java.io.Serializable;

/**
 * This class represents a related domain with a type, an ID, a role, and a referred type.
 * It implements Comparable interface to provide a natural ordering of its instances.
 *
 * @param <ID> the type of the related domain ID, which must be comparable and serializable
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedDomain<ID extends Comparable<ID> & Serializable> extends RelatedDomainAbstract<ID> {


    @Builder
    public RelatedDomain(ID id, ID parentId, String name, ID relatedId, String role, String referringType, String status, Boolean enabled, TimePeriod validFor, AuditInfo auditInfo) {
        super(id, parentId, name, relatedId, role, referringType, status, enabled, validFor, auditInfo);
    }
}