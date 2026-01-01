package ir.msob.jima.core.commons.childdomain.contactmedium;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.childdomain.BaseChildDomainAbstract;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriod;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * The 'ContactMediumAbstract' class represents a contact medium with a name, type, value, order, and validity period.
 * It extends 'BaseElementAbstract' and implements 'Comparable' for sorting based on the contact medium.
 * <p>
 * Fields:
 * - name: The name of the contact medium (must not be blank).
 * - type: The type of the contact medium (must not be blank).
 * - value: The value associated with the contact medium (must not be blank).
 * - order: An optional integer representing the order of the contact medium.
 * - validFor: An optional 'TimePeriod' indicating the validity duration of the contact medium.
 * <p>
 * Methods:
 * * - {@code compareTo(ContactMediumAbstract<ID> o)}: Compares this contact medium with another based on their keys.
 * <p>
 * Enum:
 * - ContactMediumType: Represents various types of contact mediums such as PHONE, EMAIL, FAX, etc.
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactMediumAbstract<ID extends Comparable<ID> & Serializable> extends BaseChildDomainAbstract<ID> implements Comparable<ContactMediumAbstract<ID>> {

    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String value;
    private Integer order;
    private TimePeriod validFor;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContactMediumAbstract<?> that = (ContactMediumAbstract<?>) obj;
        return name.equals(that.name) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, type);
    }

    @Override
    public int compareTo(ContactMediumAbstract<ID> o) {
        int nameComparison = this.name.compareTo(o.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.type.compareTo(o.type);
    }

    public enum ContactMediumType {
        PHONE,
        EMAIL,
        FAX,
        WEBSITE,
        SOCIAL_MEDIA,
        POSTAL_ADDRESS,
        OTHER
    }
}