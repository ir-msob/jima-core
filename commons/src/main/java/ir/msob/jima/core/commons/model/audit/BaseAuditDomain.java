package ir.msob.jima.core.commons.model.audit;

import java.io.Serializable;
import java.util.Comparator;
import java.util.SortedSet;

/**
 * @author Yaqub Abdi
 */
public interface BaseAuditDomain<ID extends Comparable<ID> & Serializable> {

    SortedSet<AuditDomain<ID>> getAuditDomains();

    void setAuditDomains(SortedSet<AuditDomain<ID>> auditDomains);

    default AuditDomain<ID> getLatestAuditDomain() {
        return getAuditDomains()
                .stream()
                .max(Comparator.comparing(AuditDomain::getVersion))
                .orElse(null);
    }

}
