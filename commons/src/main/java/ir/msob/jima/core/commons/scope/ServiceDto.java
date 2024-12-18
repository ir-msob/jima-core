package ir.msob.jima.core.commons.scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.shared.ModelType;
import lombok.*;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Data Transfer Object (DTO) for services.
 * Represents a service with a name and a set of associated resources.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDto extends ModelType {
    /**
     * The name of the service.
     */
    private String serviceName;

    /**
     * A sorted set of resources associated with the service.
     */
    @Builder.Default
    private SortedSet<ResourceDto> resources = new TreeSet<>();
}
