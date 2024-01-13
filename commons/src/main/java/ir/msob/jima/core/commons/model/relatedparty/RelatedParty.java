package ir.msob.jima.core.commons.model.relatedparty;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatedParty<ID extends Comparable<ID> & Serializable> implements Comparable<RelatedParty<ID>>, Serializable {
    @NotBlank
    private String relatedPartyType;
    @NotNull
    private ID relatedPartyId;
    private String role;
    private String referredType;

    @Override
    public int compareTo(RelatedParty<ID> o) {
        if (this == o) {
            return 0;
        }

        int idCompare = Objects.compare(this.getRelatedPartyId(), o.getRelatedPartyId(), Comparable::compareTo);
        if (idCompare != 0) {
            return idCompare;
        }

        int typeCompare = Objects.compare(this.getRelatedPartyType(), o.getRelatedPartyType(), String::compareTo);
        if (typeCompare != 0) {
            return typeCompare;
        }

        int roleCompare = Objects.compare(this.getRole(), o.getRole(), String::compareTo);
        if (roleCompare != 0) {
            return roleCompare;
        }

        return Objects.compare(this.getReferredType(), o.getReferredType(), String::compareTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relatedPartyType, relatedPartyId, role, referredType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (o instanceof RelatedParty<?> relatedParty) {
            RelatedParty<ID> that = (RelatedParty<ID>) relatedParty;
            return Objects.equals(this.getRelatedPartyId(), that.getRelatedPartyId())
                    && Objects.equals(this.getRelatedPartyType(), that.getRelatedPartyType())
                    && Objects.equals(this.getRole(), that.getRole())
                    && Objects.equals(this.getReferredType(), that.getReferredType());
        }
        return false;
    }

    public enum FN {
        relatedPartyType, relatedPartyId, role, referredType
    }
}
