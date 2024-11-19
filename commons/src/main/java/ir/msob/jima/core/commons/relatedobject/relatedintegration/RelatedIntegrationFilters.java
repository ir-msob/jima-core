package ir.msob.jima.core.commons.relatedobject.relatedintegration;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.relatedobject.RelatedObjectFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents the filters that can be applied when searching for related integrations.
 * It extends the BaseRelatedFilters class and provides filters for the related integration type, ID, role, and referred type.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedIntegrationFilters extends RelatedObjectFilters<String> {
}