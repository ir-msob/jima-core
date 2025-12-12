package ir.msob.jima.core.ral.r2dbc.it.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.DtoInfo;
import ir.msob.jima.core.it.Microservices;
import ir.msob.jima.core.it.dto.ProjectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@DtoInfo(serviceName = Microservices.TEST_MICROSERVICE, version = Microservices.VERSION)
public class TestDto extends TestDomain implements ProjectDto {
    private String dtoField;

    public TestDto(String id) {
        super(id);
    }

    public enum FN {
        dtoField
    }
}
