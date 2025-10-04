package ir.msob.jima.core.commons.shared.auditinfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.shared.BaseModel;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

/**
 * Represents audit information for an entity.
 * This class contains details about the creation and update timestamps.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditInfo implements BaseModel {

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

    /**
     * The user who created the entity.
     * Can be set to null if unknown.
     */
    @NotBlank
    private String createdBy;

    /**
     * The user who last updated the entity.
     * Can be set to null if entity has not been updated.
     */
    private String updatedBy;
}
