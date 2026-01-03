package ir.msob.jima.core.commons.childdomain.relatedobject;

import ir.msob.jima.core.commons.childdomain.BaseChildDomain;
import ir.msob.jima.core.commons.shared.auditinfo.AuditInfo;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriod;
import org.jspecify.annotations.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * The {@code RelatedObject} interface defines the contract for related objects
 * with common properties such as name, relatedId, role, referringType, status,
 * enabled flag, validFor period, and auditInfo.
 *
 * @param <ID>  the type of the object ID
 * @param <RID> the type of the related object ID
 */
public interface BaseRelatedObject<ID extends Comparable<ID> & Serializable,
        RID extends Comparable<RID> & Serializable>
        extends BaseChildDomain<ID>, Comparable<BaseRelatedObject<ID, RID>>, Serializable {

    String getName();

    void setName(String name);

    RID getRelatedId();

    void setRelatedId(RID relatedId);

    String getRole();

    void setRole(String role);

    String getReferringType();

    void setReferringType(String referringType);

    String getStatus();

    void setStatus(String status);

    Boolean getEnabled();

    void setEnabled(Boolean enabled);

    TimePeriod getValidFor();

    void setValidFor(TimePeriod validFor);

    AuditInfo getAuditInfo();

    void setAuditInfo(AuditInfo auditInfo);

    /**
     * Compares this related object with the specified related object for order.
     *
     * @param other the related object to be compared
     * @return a negative integer, zero, or a positive integer as this related object is less than, equal to, or greater than the specified related object
     */
    @Override
    default int compareTo(@NonNull BaseRelatedObject<ID, RID> other) {
        if (this == other) {
            return 0;
        }

        int idComparison = Objects.compare(this.getRelatedId(), other.getRelatedId(), Comparable::compareTo);
        if (idComparison != 0) {
            return idComparison;
        }

        int nameComparison = Objects.compare(this.getName(), other.getName(), String::compareTo);
        if (nameComparison != 0) {
            return nameComparison;
        }

        int roleComparison = Objects.compare(this.getRole(), other.getRole(), String::compareTo);
        if (roleComparison != 0) {
            return roleComparison;
        }

        return Objects.compare(this.getReferringType(), other.getReferringType(), String::compareTo);
    }
}
