package ir.msob.jima.core.commons.util;

import com.google.common.collect.Sets;
import ir.msob.jima.core.commons.shared.criteria.BaseCriteria;
import ir.msob.jima.core.commons.shared.criteria.BaseCriteriaAbstract;
import ir.msob.jima.core.commons.shared.criteria.filter.Filter;
import lombok.SneakyThrows;

import java.io.Serializable;
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
    @SneakyThrows
    public static <ID extends Comparable<ID> & Serializable, C extends BaseCriteria<ID>> C idCriteria(Class<C> criteriaClass, ID... ids) {
        C criteria = criteriaClass.getDeclaredConstructor().newInstance();
        criteria.setId(Filter.in(Sets.newHashSet(ids)));
        return criteria;
    }
}
