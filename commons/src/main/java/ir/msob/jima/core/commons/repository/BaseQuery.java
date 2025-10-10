package ir.msob.jima.core.commons.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;

/**
 * The 'BaseQuery' interface is a marker interface that represents a query in the application.
 * It does not define any methods, and is used to type-check objects at compile time and to define a contract for classes.
 * This interface can be implemented by any class that represents a query.
 */
public interface BaseQuery {

    BaseQuery with(Pageable pageable);

    BaseQuery add(Pageable pageable);

    BaseQuery withSort(String field, Sort.Direction direction);

    BaseQuery withSort(Sort sort);

    BaseQuery limit(Integer limit);

    BaseQuery regex(String field, Object value);

    BaseQuery is(String field, Object value);

    BaseQuery gte(String field, Object value);

    BaseQuery gt(String field, Object value);

    BaseQuery lt(String field, Object value);

    BaseQuery lte(String field, Object value);

    BaseQuery exists(String field, Boolean value);

    BaseQuery ne(String field, Object value);

    BaseQuery in(String field, Collection<?> value);

    BaseQuery nin(String field, Collection<?> value);

    BaseQuery include(Collection<String> fieldList);

    BaseQuery include(String field);

    BaseQuery exclude(String... fieldList);
}