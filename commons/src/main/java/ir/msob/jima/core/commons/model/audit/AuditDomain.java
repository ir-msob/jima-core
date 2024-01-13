package ir.msob.jima.core.commons.model.audit;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditDomain<ID extends Comparable<ID> & Serializable> implements Comparable<AuditDomain<ID>>, Serializable {
    @NotNull
    private ID relatedPartyId;
    @NotNull
    private Instant actionDate;
    @NotNull
    private AuditDomainActionType actionType;
    private Long version = 0L;

    @Override
    public int compareTo(AuditDomain<ID> o) {
        if (this == o) {
            return 0;
        }

        int compare = Objects.compare(this.getVersion(), o.getVersion(), Long::compareTo);
        if (compare != 0) {
            return compare;
        }

        compare = Objects.compare(this.getActionDate(), o.getActionDate(), Instant::compareTo);
        if (compare != 0) {
            return compare;
        }

        compare = Objects.compare(this.getRelatedPartyId(), o.getRelatedPartyId(), ID::compareTo);
        if (compare != 0) {
            return compare;
        }

        return Objects.compare(this.getActionType(), o.getActionType(), AuditDomainActionType::compareTo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (o instanceof AuditDomain<?> auditDomain) {
            AuditDomain<ID> that = (AuditDomain<ID>) auditDomain;
            return Objects.equals(this.getRelatedPartyId(), that.getRelatedPartyId())
                    && Objects.equals(this.getActionDate(), that.getActionDate())
                    && Objects.equals(this.getActionType(), that.getActionType())
                    && Objects.equals(this.getVersion(), that.getVersion());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(relatedPartyId, actionDate, actionType, version);
    }

    public enum FN {
        relatedPartyId, actionDate, actionType, version
    }
}
