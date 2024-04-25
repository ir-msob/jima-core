package ir.msob.jima.core.commons.model.relatedparty;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.filter.BaseFilters;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents the filters that can be applied when searching for related parties.
 * It implements the BaseFilters interface and provides filters for the related party type, ID, role, and referred type.
 *
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedPartyFilters implements BaseFilters {
    /**
     * Filter for the type of the related party.
     */
    private Filter<String> relatedPartyType;

    /**
     * Filter for the ID of the related party.
     */
    private Filter<String> relatedPartyId;

    /**
     * Filter for the role of the related party.
     */
    private Filter<String> role;

    /**
     * Filter for the type of the entity that referred to this related party.
     */
    private Filter<String> referredType;
}