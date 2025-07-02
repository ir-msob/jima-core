package ir.msob.jima.core.commons.childdomain.auditdomain;

import ir.msob.jima.core.commons.childdomain.relatedobject.relatedparty.RelatedPartyAbstract;

import java.io.Serializable;
import java.util.SortedSet;

public interface BaseAuditDomainContainer<ID extends Comparable<ID> & Serializable, RP extends RelatedPartyAbstract<ID>, AD extends AuditDomainAbstract<ID, RP>> {
    SortedSet<AD> getAuditDomains();

    void setAuditDomains(SortedSet<AD> auditDomains);
}
