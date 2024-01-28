package ir.msob.jima.core.ral.mongo.it.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.ral.mongo.it.dto.ProjectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;


@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDto extends TestDomain implements ProjectDto {
    @Transient
    public static final String DOMAIN_NAME = "TestDomain";
    @Transient
    public static final String DOMAIN_URI = "test-domain";

    private String dtoField;

    public enum FN {
        dtoField
    }
}
