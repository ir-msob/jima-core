package ir.msob.jima.core.ral.mongo.it.testchild;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.DtoInfo;
import ir.msob.jima.core.it.Microservices;
import ir.msob.jima.core.it.childdto.ProjectChildDto;
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
public class TestChildDto extends TestChildDomain implements ProjectChildDto {
    private String dtoField;

    public TestChildDto(String id, String parentId) {
        super(id, parentId);
    }

    public enum FN {
        dtoField
    }
}
