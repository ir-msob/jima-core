package ir.msob.jima.core.commons.audit;

import ir.msob.jima.core.commons.relatedobject.relatedparty.RelatedParty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The {@code BaseAuditDomainAbstract} class is an abstract class that implements the {@code BaseAuditDomain} interface.
 * It includes a field for a sorted set of audit domains and getter and setter methods for this field.
 * The class uses the {@code TreeSet} class to provide a sorted set implementation.
 * Additionally, it provides a no-argument constructor and a {@code toString} method that calls the superclass's {@code toString} method.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseAuditDomainAbstract<RP extends RelatedParty> implements BaseAuditDomain<RP> {
    /**
     * A sorted set of audit domains.
     */
    private SortedSet<AuditDomain<RP>> auditDomains = new TreeSet<>();
}