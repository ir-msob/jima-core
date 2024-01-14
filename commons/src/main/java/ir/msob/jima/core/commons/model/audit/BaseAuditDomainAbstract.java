package ir.msob.jima.core.commons.model.audit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The 'BaseAuditDomainAbstract' class is an abstract class that implements the 'BaseAuditDomain' interface.
 * It includes a field for a sorted set of audit domains and getter and setter methods for this field.
 * The class uses the 'TreeSet' class to provide a sorted set implementation.
 * It also includes a no-argument constructor and a 'toString' method that calls the superclass's 'toString' method.
 * The class is parameterized with a type 'ID' that extends 'Comparable' and 'Serializable'.
 *
 * @param <ID> The type of ID.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseAuditDomainAbstract<ID extends Comparable<ID> & Serializable> implements BaseAuditDomain<ID> {
    /**
     * A sorted set of audit domains.
     */
    private SortedSet<AuditDomain<ID>> auditDomains = new TreeSet<>();
}