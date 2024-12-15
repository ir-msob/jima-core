package ir.msob.jima.core.ral.mongo.it.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.ral.mongo.it.dto.ProjectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;


@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDto extends TestDomain implements ProjectDto {
    private String dtoField;

    public TestDto(ObjectId objectId) {
        super(objectId);
    }

    public enum FN {
        dtoField
    }
}
