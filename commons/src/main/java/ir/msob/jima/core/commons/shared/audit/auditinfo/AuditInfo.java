package ir.msob.jima.core.commons.shared.audit.auditinfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

/**
 * Represents audit information for an entity.
 * This class contains details about the creation and update timestamps.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditInfo {
    /**
     * The timestamp when the entity was created.
     * This field is automatically set to the current time when an instance is created.
     */
    @Builder.Default
    private Instant createdAt = Instant.now();

    /**
     * The timestamp when the entity was last updated.
     * This field can be set to null if the entity has not been updated.
     */
    private Instant updatedAt;
}
