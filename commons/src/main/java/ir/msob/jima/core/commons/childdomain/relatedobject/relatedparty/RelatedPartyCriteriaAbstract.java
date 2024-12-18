package ir.msob.jima.core.commons.childdomain.relatedobject.relatedparty;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.childdomain.relatedobject.RelatedObjectCriteriaAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * This class represents the filters that can be applied when searching for childdomain parties.
 * It implements the BaseFilters interface and provides filters for the childdomain party type, ID, role, and referred type.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RelatedPartyCriteriaAbstract<ID extends Comparable<ID> & Serializable, RM extends RelatedPartyAbstract<ID>> extends RelatedObjectCriteriaAbstract<ID, String, RM> {

}