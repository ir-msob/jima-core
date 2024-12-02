package ir.msob.jima.core.commons.shared.audit.auditinfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

/**
 * Represents filters for audit information in the system.
 * This class allows filtering of audit records based on creation and update timestamps.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditInfoFilters implements BaseFilters {

    /**
     * Filter for the creation timestamp of the audit record.
     * This filter can be used to specify a range or exact match for the createdAt field.
     */
    private Filter<Instant> createdAt;

    /**
     * Filter for the last update timestamp of the audit record.
     * This filter can be used to specify a range or exact match for the updatedAt field.
     */
    private Filter<Instant> updatedAt;

    public static boolean isMatching(AuditInfoFilters filter, AuditInfo auditInfo) {
        if (filter != null) {
            return filter.isMatching(auditInfo);
        }
        return true;
    }

    public boolean isMatching(AuditInfo auditInfo) {
        if (this.getCreatedAt() != null) {
            if (auditInfo == null) {
                if (!this.getCreatedAt().isMatching(null)) {
                    return false;
                }
            } else {
                if (!this.getCreatedAt().isMatching(auditInfo.getCreatedAt())) {
                    return false;
                }
            }
        }
        if (this.getUpdatedAt() != null) {
            if (auditInfo == null) {
                return this.getUpdatedAt().isMatching(null);
            } else {
                return this.getUpdatedAt().isMatching(auditInfo.getUpdatedAt());
            }
        }
        return true;
    }
}