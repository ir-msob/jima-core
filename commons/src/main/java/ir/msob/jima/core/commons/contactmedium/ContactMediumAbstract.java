package ir.msob.jima.core.commons.contactmedium;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.BaseIdModelAbstract;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriod;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * The 'ContactMediumAbstract' class represents a contact medium with a name, type, value, order, and validity period.
 * It extends 'BaseIdModelAbstract' and implements 'Comparable' for sorting based on the contact medium.
 * <p>
 * Fields:
 * - name: The name of the contact medium (must not be blank).
 * - type: The type of the contact medium (must not be blank).
 * - value: The value associated with the contact medium (must not be blank).
 * - order: An optional integer representing the order of the contact medium.
 * - validFor: An optional 'TimePeriod' indicating the validity duration of the contact medium.
 * <p>
 * Methods:
 * - compareTo(ContactMediumAbstract<ID> o): Compares this contact medium with another based on their keys.
 * <p>
 * Enum:
 * - ContactMediumType: Represents various types of contact mediums such as PHONE, EMAIL, FAX, etc.
 */
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactMediumAbstract<ID extends Comparable<ID> & Serializable> extends BaseIdModelAbstract<ID> implements Comparable<ContactMediumAbstract<ID>> {

    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String value;
    private Integer order;
    private TimePeriod validFor;


    @Override
    public int compareTo(ContactMediumAbstract<ID> o) {
        return 0;
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