package ir.msob.jima.core.commons.model.audit;

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
public abstract class BaseAuditDomainAbstract implements BaseAuditDomain {
    /**
     * A sorted set of audit domains.
     */
    private SortedSet<AuditDomain> auditDomains = new TreeSet<>();
}