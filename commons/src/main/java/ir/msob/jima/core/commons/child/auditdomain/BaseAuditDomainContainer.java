package ir.msob.jima.core.commons.child.auditdomain;

import ir.msob.jima.core.commons.child.BaseContainer;
import ir.msob.jima.core.commons.child.relatedobject.relatedparty.RelatedPartyAbstract;

import java.io.Serializable;
import java.util.Comparator;
import java.util.SortedSet;

/**
 * The 'BaseAuditDomainContainer' interface represents a base interface for audit domain classes.
 * It includes methods for getting and setting a sorted set of audit domains, and a default method for getting the latest audit domain.
 * The 'getLatestAuditDomain' method uses the Java 8 Stream API to find the audit domain with the maximum version.
 * The interface is parameterized with a type 'ID' that extends 'Comparable' and 'Serializable'.
 */
public interface BaseAuditDomainContainer<ID extends Comparable<ID> & Serializable, RP extends RelatedPartyAbstract<ID>> extends BaseContainer {

    /**
     * Get a sorted set of audit domains.
     *
     * @return A sorted set of audit domains.
     */
    SortedSet<AuditDomainAbstract<ID, RP>> getAuditDomains();

    /**
     * Set a sorted set of audit domains.
     *
     * @param auditDomains A sorted set of audit domains.
     */
    void setAuditDomains(SortedSet<AuditDomainAbstract<ID, RP>> auditDomains);

    /**
     * Get the latest audit domain.
     * This method uses the Java 8 Stream API to find the audit domain with the maximum version.
     *
     * @return The latest audit domain, or null if there are no audit domains.
     */
    default AuditDomainAbstract<ID, RP> getLatestAuditDomain() {
        return getAuditDomains()
                .stream()
                .max(Comparator.comparing(AuditDomainAbstract::getVersion))
                .orElse(null);
    }

}