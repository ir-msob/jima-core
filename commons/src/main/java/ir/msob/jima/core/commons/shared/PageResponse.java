package ir.msob.jima.core.commons.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

/**
 * A wrapper around PageImpl that avoids Jackson2PageModule and
 * handles (de)serialization safely in WebFlux.
 */
@JsonIgnoreProperties({"pageable"})
public class PageResponse<T> extends PageImpl<T> {

    private static final long serialVersionUID = 1L;

    // Default constructor (Jackson needs it)
    public PageResponse() {
        super(Collections.emptyList());
    }

    public PageResponse(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PageResponse(List<T> content) {
        super(content);
    }

    /**
     * Jackson-friendly constructor: we ignore Pageable and build it from number & size.
     */
    @JsonCreator
    public PageResponse(
            @JsonProperty("content") List<T> content,
            @JsonProperty("number") int number,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") long totalElements) {

        super(content, PageRequest.of(number, size), totalElements);
    }
}
