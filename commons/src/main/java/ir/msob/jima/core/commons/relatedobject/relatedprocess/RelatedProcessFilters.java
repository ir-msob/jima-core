package ir.msob.jima.core.commons.relatedobject.relatedprocess;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.relatedobject.RelatedObjectFilters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents the filters that can be applied when searching for related processes.
 * It extends the BaseRelatedFilters class and provides filters for the related process type, ID, role, and referred type.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedProcessFilters extends RelatedObjectFilters<String> {
}