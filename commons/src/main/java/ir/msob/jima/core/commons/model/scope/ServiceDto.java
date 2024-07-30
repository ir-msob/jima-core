package ir.msob.jima.core.commons.model.scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.dto.ModelType;
import lombok.*;

import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDto extends ModelType {
    private String serviceName;
    private SortedSet<ResourceDto> resources = new TreeSet<>();
}
