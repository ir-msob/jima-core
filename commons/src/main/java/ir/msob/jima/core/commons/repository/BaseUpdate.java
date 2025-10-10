package ir.msob.jima.core.commons.repository;

import java.util.Collection;

/**
 * The 'BaseUpdate' interface is a marker interface that represents an update operation in the application.
 * It does not define any methods, and is used to type-check objects at compile time and to define a contract for classes.
 * This interface can be implemented by any class that represents an update operation.
 */
public interface BaseUpdate {

    /**
     * @param field
     * @param value
     * @return
     */
    BaseUpdate set(String field, Object value);

    /**
     * @param field
     * @return
     */
    BaseUpdate unset(String field);

    /**
     * @param field
     * @param inc
     * @return
     */
    BaseUpdate inc(String field, Number inc);

    /**
     * @param field
     * @param value
     * @return
     */
    BaseUpdate pull(String field, Object value);

    /**
     * @param field
     * @param valueList
     * @return
     */
    BaseUpdate pullAll(String field, Collection<?> valueList);

    /**
     * @param field
     * @param value
     * @return
     */
    BaseUpdate push(String field, Object value);

    /**
     * @param field
     * @param value
     * @param slice
     * @return
     */
    BaseUpdate push(String field, Object value, Integer slice);

    /**
     * @param field
     * @param values
     * @return
     */
    BaseUpdate pushAll(String field, Collection<?> values);

    /**
     * @param field
     * @param values
     * @param slice
     * @return
     */
    BaseUpdate pushAll(String field, Collection<?> values, Integer slice);

    /**
     * @param field
     * @param value
     * @return
     */
    BaseUpdate addToSet(String field, Object value);

    /**
     * @param field
     * @param values
     * @return
     */
    BaseUpdate addToSetAll(String field, Collection<?> values);
}