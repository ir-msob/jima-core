package ir.msob.jima.core.commons.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A JSON-friendly, framework-agnostic implementation of org.springframework.data.domain.Page
 * Contains conversion helpers: from(Page) and toPage()
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDto<T> implements Page<T> {

    private final List<T> content;
    private final PageableDto pageable;
    private final long totalElements;
    private final int totalPages;

    public PageDto() {
        this.content = Collections.emptyList();
        this.pageable = PageableDto.unpaged();
        this.totalElements = 0L;
        this.totalPages = 0;
    }

    @JsonCreator
    public PageDto(
            @JsonProperty("content") List<T> content,
            @JsonProperty("pageable") PageableDto pageable,
            @JsonProperty("totalElements") Long totalElements,
            @JsonProperty("totalPages") Integer totalPages
    ) {
        this.content = content == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList<>(content));
        this.pageable = pageable == null ? PageableDto.unpaged() : pageable;
        this.totalElements = totalElements == null ? 0L : Math.max(0L, totalElements);

        if (totalPages != null) {
            this.totalPages = Math.max(0, totalPages);
        } else if (this.pageable.isUnpaged()) {
            this.totalPages = this.totalElements > 0 ? 1 : 0;
        } else {
            int size = Math.max(1, this.pageable.getPageSize());
            this.totalPages = (int) ((this.totalElements + size - 1) / (long) size);
        }
    }

    // Convenience constructor for server-side creation
    public PageDto(List<T> content, PageableDto pageable, long totalElements) {
        this(content, pageable, totalElements, null);
    }

    // ---------- Conversion helpers (inside the DTO) ----------

    /**
     * Build PageDto from a Spring Data Page
     */
    public static <T> PageDto<T> from(Page<T> page) {
        if (page == null) {
            return PageDto.empty();
        }

        Pageable pageable = page.getPageable();
        PageableDto pageableDto = PageableDto.from(pageable);

        return new PageDto<>(
                page.getContent(),
                pageableDto,
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    // static helper to create an empty PageDto
    public static <T> PageDto<T> empty() {
        return new PageDto<>(Collections.emptyList(), PageableDto.unpaged(), 0L, 0);
    }

    // ---------- Page methods ----------

    /**
     * Convert this PageDto into a Spring Data Page (PageImpl).
     * Useful when you need to call repository/service APIs that expect {@code Page<T>}.
     */
    public Page<T> toPage() {
        Pageable pg = (this.pageable == null || this.pageable.isUnpaged()) ? Pageable.unpaged() : this.pageable.toPageable();
        return new PageImpl<>(this.content, pg, this.totalElements);
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        List<U> mapped = this.content.stream().map(converter).collect(Collectors.toList());
        return new PageDto<>(mapped, this.pageable, this.totalElements, this.totalPages);
    }

    @Override
    public int getNumber() {
        return pageable == null || pageable.isUnpaged() ? 0 : pageable.getPageNumber();
    }

    @Override
    public int getSize() {
        return pageable == null || pageable.isUnpaged() ? content.size() : pageable.getPageSize();
    }

    @Override
    public int getNumberOfElements() {
        return content.size();
    }

    @Override
    public boolean hasContent() {
        return !content.isEmpty();
    }

    @Override
    public Sort getSort() {
        return pageable == null ? Sort.unsorted() : pageable.getSort();
    }

    @Override
    public boolean isFirst() {
        return pageable == null || pageable.isUnpaged() || !pageable.hasPrevious();
    }

    @Override
    public boolean isLast() {
        if (pageable == null || pageable.isUnpaged()) {
            return totalPages <= 1;
        }
        return getNumber() + 1 >= getTotalPages();
    }

    @Override
    public boolean hasNext() {
        if (pageable == null || pageable.isUnpaged()) return false;
        return getNumber() + 1 < getTotalPages();
    }

    @Override
    public boolean hasPrevious() {
        return pageable != null && pageable.hasPrevious();
    }

    @Override
    public Pageable nextPageable() {
        return pageable == null || pageable.isUnpaged() ? null : pageable.next();
    }

    @Override
    public Pageable previousPageable() {
        return pageable == null || pageable.isUnpaged() ? null : pageable.previousOrFirst();
    }

    // ---------- JSON getters / convenience getters ----------

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    @JsonProperty("content")
    public List<T> getJsonContent() {
        return content;
    }

    @JsonProperty("totalElements")
    public long getJsonTotalElements() {
        return totalElements;
    }

    @JsonProperty("totalPages")
    public int getJsonTotalPages() {
        return totalPages;
    }
}
