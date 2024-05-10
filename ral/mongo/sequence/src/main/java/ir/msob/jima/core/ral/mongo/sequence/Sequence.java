package ir.msob.jima.core.ral.mongo.sequence;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.domain.BaseDomain;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Sequence.DOMAIN_NAME)
public class Sequence implements BaseDomain<String> {
    @Transient
    public static final String DOMAIN_NAME = "Sequence";
    private String id;
    @Field
    private long value = 1L;

    @Override
    public String getDomainId() {
        return getId();
    }

    @Override
    public void setDomainId(String s) {
        setId(s);
    }

    @Override
    public String getDomainIdName() {
        return FN.id.name();
    }

    public enum FN {
        id, value
    }
}
