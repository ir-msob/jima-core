package ir.msob.jima.core.ral.mongo.sequence;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.domain.BaseDomain;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents a sequence entity in MongoDB.
 * This class is used to manage sequences for generating unique identifiers.
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Sequence.DOMAIN_NAME)
public class Sequence implements BaseDomain<String> {

    /**
     * The name of the MongoDB collection.
     */
    @Transient
    public static final String DOMAIN_NAME = "Sequence";

    /**
     * The unique identifier of the sequence.
     */
    private String id;

    /**
     * The current value of the sequence.
     */
    @Field
    @Builder.Default
    private long value = 1L;

    /**
     * Retrieves the domain ID of the sequence.
     *
     * @return the ID of the sequence.
     */
    @Override
    public String getDomainId() {
        return getId();
    }

    /**
     * Sets the domain ID of the sequence.
     *
     * @param s the ID to set.
     */
    @Override
    public void setDomainId(String s) {
        setId(s);
    }

    /**
     * Retrieves the name of the domain ID field.
     *
     * @return the name of the ID field.
     */
    @Override
    public String getDomainIdName() {
        return FN.id.name();
    }

    /**
     * Enum representing the field names of the sequence.
     */
    public enum FN {
        id, value
    }
}
