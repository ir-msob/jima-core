package ir.msob.jima.core.ral.mongo.it.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.ral.mongo.it.dto.ProjectDto;
import lombok.*;


@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDto extends TestDomain implements ProjectDto {
    private String dtoField;

    public enum FN {
        dtoField
    }
}
