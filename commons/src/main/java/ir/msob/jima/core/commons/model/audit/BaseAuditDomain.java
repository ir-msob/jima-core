package ir.msob.jima.core.commons.model.audit;

import java.io.Serializable;
import java.util.Comparator;
import java.util.SortedSet;

/**
 * The 'BaseAuditDomain' interface represents a base interface for audit domain classes.
 * It includes methods for getting and setting a sorted set of audit domains, and a default method for getting the latest audit domain.
 * The 'getLatestAuditDomain' method uses the Java 8 Stream API to find the audit domain with the maximum version.
 * The interface is parameterized with a type 'ID' that extends 'Comparable' and 'Serializable'.
 *
 * @param <ID> The type of ID.
 *
 */
public interface BaseAuditDomain<ID extends Comparable<ID> & Serializable> {

    /**
     * Get a sorted set of audit domains.
     *
     * @return A sorted set of audit domains.
     */
    SortedSet<AuditDomain<ID>> getAuditDomains();

    /**
     * Set a sorted set of audit domains.
     *
     * @param auditDomains A sorted set of audit domains.
     */
    void setAuditDomains(SortedSet<AuditDomain<ID>> auditDomains);

    /**
     * Get the latest audit domain.
     * This method uses the Java 8 Stream API to find the audit domain with the maximum version.
     *
     * @return The latest audit domain, or null if there are no audit domains.
     */
    default AuditDomain<ID> getLatestAuditDomain() {
        return getAuditDomains()
                .stream()
                .max(Comparator.comparing(AuditDomain::getVersion))
                .orElse(null);
    }

}