package ir.msob.jima.core.commons.model.audit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BaseAuditDomainAbstract<ID extends Comparable<ID> & Serializable> implements BaseAuditDomain<ID> {
    private SortedSet<AuditDomain<ID>> auditDomains = new TreeSet<>();

}
