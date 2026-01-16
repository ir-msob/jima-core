package ir.msob.jima.core.commons.util;

import com.google.common.collect.Sets;
import ir.msob.jima.core.commons.domain.BaseCriteria;
import ir.msob.jima.core.commons.domain.BaseCriteriaAbstract;
import ir.msob.jima.core.commons.filter.Filter;
import ir.msob.jima.core.commons.safemodify.UniqueField;
import lombok.SneakyThrows;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Utility class for creating criteria objects with ID-based filters.
 */
public class CriteriaUtil {
    private CriteriaUtil() {
    }

    /**
     * Creates a criteria object with an ID filter for a single ID.
     *
     * @param id   The ID to filter.
     * @param <ID> The type of ID.
     * @param <C>  The type of BaseCriteria.
     * @return The criteria object with the ID filter.
     */
    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> C idCriteria(ID id) {
        @SuppressWarnings("unchecked")
        C criteria = (C) new BaseCriteriaAbstract<ID>() {

        };
        criteria.setId(Filter.eq(id));
        return criteria;
    }

    /**
     * Creates a criteria object with an ID filter for a single ID.
     *
     * @param criteriaClass The class of the criteria object.
     * @param id            The ID to filter.
     * @param <ID>          The type of ID.
     * @param <C>           The type of BaseCriteria.
     * @return The criteria object with the ID filter.
     */
    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> C idCriteria(Class<C> criteriaClass, ID id) {
        C criteria = criteriaClass.getDeclaredConstructor().newInstance();
        criteria.setId(Filter.eq(id));
        return criteria;
    }

    /**
     * Creates a criteria object with an ID filter for a collection of IDs.
     *
     * @param criteriaClass The class of the criteria object.
     * @param ids           The collection of IDs to filter.
     * @param <ID>          The type of ID.
     * @param <C>           The type of BaseCriteria.
     * @return The criteria object with the ID filter.
     */
    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> C idCriteria(Class<C> criteriaClass, Collection<ID> ids) {
        C criteria = criteriaClass.getDeclaredConstructor().newInstance();
        criteria.setId(Filter.in(Sets.newHashSet(ids)));
        return criteria;
    }

    /**
     * Creates a criteria object with an ID filter for a collection of IDs.
     *
     * @param ids  The collection of IDs to filter.
     * @param <ID> The type of ID.
     * @param <C>  The type of BaseCriteria.
     * @return The criteria object with the ID filter.
     */
    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> C idCriteria(Collection<ID> ids) {
        @SuppressWarnings("unchecked")
        C criteria = (C) new BaseCriteriaAbstract<ID>() {
        };
        criteria.setId(Filter.in(Sets.newHashSet(ids)));
        return criteria;
    }

    /**
     * Creates a criteria object with an ID filter for an array of IDs.
     *
     * @param criteriaClass The class of the criteria object.
     * @param ids           The array of IDs to filter.
     * @param <ID>          The type of ID.
     * @param <C>           The type of BaseCriteria.
     * @return The criteria object with the ID filter.
     */
    @SafeVarargs
    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> C idCriteria(Class<C> criteriaClass, ID... ids) {
        C criteria = criteriaClass.getDeclaredConstructor().newInstance();
        criteria.setId(Filter.in(Sets.newHashSet(ids)));
        return criteria;
    }

    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> C uniqueCriteria(Class<C> criteriaClass, String uniqueField) {
        C criteria = criteriaClass.getDeclaredConstructor().newInstance();
        Field field = UniqueField.info.getFirstFieldHasAnnotation(criteria.getClass());
        Assert.notNull(field, "Unique Field Not Found in Criteria");
        field.set(criteria, Filter.eq(uniqueField));
        return criteria;
    }
}
