package ir.msob.jima.core.commons.model.relateddomain;

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
public class RelatedDomain<ID extends Comparable<ID> & Serializable> implements Comparable<RelatedDomain<ID>>, Serializable {
    @NotBlank
    private String relatedDomainType;
    @NotNull
    private ID relatedDomainId;
    private String role;
    private String referredType;

    @Override
    public int compareTo(RelatedDomain<ID> o) {
        if (this == o) {
            return 0;
        }

        int idCompare = Objects.compare(this.getRelatedDomainId(), o.getRelatedDomainId(), Comparable::compareTo);
        if (idCompare != 0) {
            return idCompare;
        }

        int typeCompare = Objects.compare(this.getRelatedDomainType(), o.getRelatedDomainType(), String::compareTo);
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
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (o instanceof RelatedDomain<?> relatedDomain) {
            RelatedDomain<ID> that = (RelatedDomain<ID>) relatedDomain;
            return Objects.equals(this.getRelatedDomainId(), that.getRelatedDomainId())
                    && Objects.equals(this.getRelatedDomainType(), that.getRelatedDomainType())
                    && Objects.equals(this.getRole(), that.getRole())
                    && Objects.equals(this.getReferredType(), that.getReferredType());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(relatedDomainType, relatedDomainId, role, referredType);
    }

    public enum FN {
        relatedDomainType, relatedDomainId, role, referredType
    }
}
