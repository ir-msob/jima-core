package ir.msob.jima.core.ral.jpa.commons.query;

import ir.msob.jima.core.commons.repository.BaseQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A thin wrapper that holds a Spring-Data JPA Specification plus pagination/sort/include/exclude info.
 * No custom condition objects — everything is a Specification (JPA/Criterion-native).
 */
@Getter
public class JpaQuery<T> implements BaseQuery {

    private final Set<String> includes = new LinkedHashSet<>();
    private final Set<String> excludes = new LinkedHashSet<>();
    private Specification<T> specification;
    private Pageable pageable;
    private Sort sort;
    private Integer limit;

    public JpaQuery() {
    }

    public JpaQuery<T> where(Specification<T> spec) {
        if (spec == null) return this;
        if (this.specification == null) this.specification = spec;
        else this.specification = this.specification.and(spec);
        return this;
    }


    public JpaQuery<T> with(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public JpaQuery<T> add(Pageable pageable) {
        return with(pageable);
    }

    public JpaQuery<T> withSort(String field, Sort.Direction direction) {
        this.sort = Sort.by(direction, field);
        return this;
    }

    public JpaQuery<T> withSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public JpaQuery<T> limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public JpaQuery<T> include(Collection<String> fields) {
        if (fields != null) this.includes.addAll(fields);
        return this;
    }

    public JpaQuery<T> include(String field) {
        if (field != null) this.includes.add(field);
        return this;
    }

    public JpaQuery<T> exclude(String... fields) {
        if (fields != null) {
            Collections.addAll(this.excludes, fields);
        }
        return this;
    }

}
