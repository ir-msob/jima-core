package ir.msob.jima.core.commons.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * A JSON-friendly implementation of org.springframework.data.domain.Pageable
 * Contains conversion helpers: from(Pageable) and toPageable()
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableDto implements Pageable, Serializable {

    private final int pageNumber;
    private final int pageSize;
    private final boolean paged;
    private final List<OrderDto> orders;

    public PageableDto() {
        this.pageNumber = 0;
        this.pageSize = 10;
        this.paged = true;
        this.orders = Collections.emptyList();
    }

    @JsonCreator
    public PageableDto(
            @JsonProperty("pageNumber") Integer pageNumber,
            @JsonProperty("pageSize") Integer pageSize,
            @JsonProperty("paged") Boolean paged,
            @JsonProperty("orders") List<OrderDto> orders
    ) {
        this.pageNumber = pageNumber == null ? 0 : Math.max(0, pageNumber);
        this.pageSize = pageSize == null ? 20 : Math.max(0, pageSize);
        this.paged = paged == null || paged;
        this.orders = orders == null ? Collections.emptyList() : List.copyOf(orders);
    }

    public static PageableDto of(int pageNumber, int pageSize, Sort sort) {
        List<OrderDto> orders = sort == null ? Collections.emptyList() :
                sort.stream()
                        .map(o -> new OrderDto(o.getProperty(), o.getDirection().name()))
                        .toList();
        return new PageableDto(pageNumber, pageSize, true, orders);
    }

    public static PageableDto unpaged() {
        return new PageableDto(0, 0, false, Collections.emptyList());
    }

    // ---------- Conversion helpers (inside the DTO) ----------

    /**
     * Build PageableDto from a Spring Data Pageable
     */
    public static PageableDto from(Pageable pageable) {
        if (pageable == null || pageable.isUnpaged()) {
            return PageableDto.unpaged();
        }
        return PageableDto.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort()
        );
    }

    /**
     * Convert this DTO to a Spring Data Pageable (PageRequest or Pageable.unpaged())
     */
    public Pageable toPageable() {
        if (this.isUnpaged()) {
            return Pageable.unpaged();
        }
        return PageRequest.of(this.pageNumber, this.pageSize, this.getSort());
    }

    // ---------- Pageable methods ----------

    @Override
    public long getOffset() {
        if (isUnpaged()) return 0L;
        return (long) pageNumber * (long) Math.max(1, pageSize);
    }

    @Override
    public @NonNull Sort getSort() {
        if (orders.isEmpty()) return Sort.unsorted();
        List<Sort.Order> list = orders.stream()
                .map(OrderDto::toOrder)
                .toList();
        return Sort.by(list);
    }

    @Override
    public boolean isPaged() {
        return this.paged && this.pageSize > 0;
    }

    @Override
    public @NonNull Pageable next() {
        return new PageableDto(this.pageNumber + 1, this.pageSize, this.paged, this.orders);
    }

    @Override
    public @NonNull Pageable previousOrFirst() {
        int prev = this.pageNumber > 0 ? this.pageNumber - 1 : 0;
        return new PageableDto(prev, this.pageSize, this.paged, this.orders);
    }

    @Override
    public @NonNull Pageable first() {
        return new PageableDto(0, this.pageSize, this.paged, this.orders);
    }

    @Override
    public @NonNull Pageable withPage(int pageNumber) {
        return new PageableDto(pageNumber, this.pageSize, this.paged, this.orders);
    }

    @Override
    public boolean hasPrevious() {
        return this.pageNumber > 0;
    }

    // JSON getters
    @JsonProperty("pageNumber")
    public int getJsonPageNumber() {
        return pageNumber;
    }

    @JsonProperty("pageSize")
    public int getJsonPageSize() {
        return pageSize;
    }

    @JsonProperty("paged")
    public boolean isJsonPaged() {
        return paged;
    }

    /**
     * @param direction "ASC" | "DESC"
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record OrderDto(String property, String direction) {
        @JsonCreator
        public OrderDto(@JsonProperty("property") String property,
                        @JsonProperty("direction") String direction) {
            this.property = property;
            this.direction = direction == null ? "ASC" : direction;
        }

        public Sort.Order toOrder() {
            Sort.Direction dir = Sort.Direction.fromString(direction);
            return new Sort.Order(dir, property);
        }
    }
}
