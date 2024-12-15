package ir.msob.jima.core.commons.child.auditdomain;

import ir.msob.jima.core.commons.child.relatedobject.relatedparty.RelatedPartyAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The {@code BaseAuditDomainContainerAbstract} class is an abstract class that implements the {@code BaseAuditDomainContainer} interface.
 * It includes a field for a sorted set of audit domains and getter and setter methods for this field.
 * The class uses the {@code TreeSet} class to provide a sorted set implementation.
 * Additionally, it provides a no-argument constructor and a {@code toString} method that calls the superclass's {@code toString} method.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseAuditDomainContainerAbstract<ID extends Comparable<ID> & Serializable, RP extends RelatedPartyAbstract<ID>> implements BaseAuditDomainContainer<ID, RP> {
    /**
     * A sorted set of audit domains.
     */
    private SortedSet<AuditDomainAbstract<ID, RP>> auditDomains = new TreeSet<>();
}