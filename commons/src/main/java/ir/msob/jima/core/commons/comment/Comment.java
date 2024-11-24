package ir.msob.jima.core.commons.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.BaseIdModelAbstract;
import ir.msob.jima.core.commons.relatedobject.RelatedObjectAbstract;
import ir.msob.jima.core.commons.relatedobject.relatedparty.RelatedPartyAbstract;
import ir.msob.jima.core.commons.shared.audit.auditinfo.AuditInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment<ID extends Comparable<ID> & Serializable, RP extends RelatedPartyAbstract> extends BaseIdModelAbstract<ID> implements Comparable<Comment<ID, RP>> {

    /**
     * A non-blank string representing the content of the comment.
     */
    @NotBlank
    private String comment;

    /**
     * An instance of {@code RelatedPartyAbstract} associated with the comment.
     */
    private RP relatedParty;

    /**
     * An instance of {@code AuditInfo} containing auditing information.
     */
    private AuditInfo auditInfo;

    /**
     * An instance of {@code RelatedObjectAbstract} associated with the comment.
     */
    private RelatedObjectAbstract<ID> relatedObject;

    /**
     * A list of nested comments, initialized to an empty list by default.
     */
    @Builder.Default
    private List<Comment<ID, RP>> comments = new ArrayList<>();

    /**
     * Checks whether this comment is equal to another object based on their content.
     *
     * @param o The other object to compare.
     * @return True if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (o instanceof Comment<?, ?> that)
            return Objects.equals(this.getComment(), that.getComment());

        return false;
    }

    /**
     * Returns the hash code of this comment based on its fields.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getComment(), getRelatedParty(), getAuditInfo());
    }

    /**
     * Compares this comment with another comment based on their content.
     *
     * @param o The other comment to compare with.
     * @return A negative integer, zero, or a positive integer as this comment is less than,
     * equal to, or greater than the specified comment.
     */
    @Override
    public int compareTo(Comment<ID, RP> o) {
        if (this == o) {
            return 0;
        }

        if (o != null && (this.getComment() != null && o.getComment() != null)) {
            return this.getComment().compareTo(o.getComment());

        }

        return Comparator
                .comparing(System::identityHashCode)
                .compare(this, o);
    }

    /**
     * The {@code FN} enum represents the field names of the class.
     */
    public enum FN {
        name, relatedParty, auditInfo, relatedObject

    }
}