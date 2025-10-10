package ir.msob.jima.core.ral.mongo.commons.query;

import ir.msob.jima.core.commons.repository.BaseUpdate;
import lombok.*;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;

/**
 * An abstract base class for building MongoDB queries.
 * This class provides methods to construct MongoDB queries and updates.
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MongoUpdate implements BaseUpdate {
    private Update update;


    /**
     * @param field
     * @param value
     * @return
     */
    public MongoUpdate set(String field, Object value) {
        update.set(field, value);
        return this;
    }

    /**
     * @param field
     * @return
     */
    public MongoUpdate unset(String field) {
        update.unset(field);
        return this;
    }

    /**
     * @param field
     * @param inc
     * @return
     */
    public MongoUpdate inc(String field, Number inc) {
        update.inc(field, inc);
        return this;
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public MongoUpdate pull(String field, Object value) {
        update.pull(field, value);
        return this;
    }

    /**
     * @param field
     * @param position
     * @return
     */
    public MongoUpdate pop(String field, Update.Position position) {
        update.pop(field, position);
        return this;
    }

    /**
     * @param field
     * @param valueList
     * @return
     */
    public MongoUpdate pullAll(String field, Collection<?> valueList) {
        update.pullAll(field, valueList.toArray());
        return this;
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public MongoUpdate push(String field, Object value) {
        update.push(field).each(value);
        return this;
    }

    /**
     * @param field
     * @param value
     * @param slice
     * @return
     */
    public MongoUpdate push(String field, Object value, Integer slice) {
        update.push(field).slice(slice).each(value);
        return this;
    }

    /**
     * @param field
     * @param values
     * @return
     */
    public MongoUpdate pushAll(String field, Collection<?> values) {
        update.push(field).each(values);
        return this;
    }

    /**
     * @param field
     * @param values
     * @param slice
     * @return
     */
    public MongoUpdate pushAll(String field, Collection<?> values, Integer slice) {
        update.push(field).slice(slice).each(values);
        return this;
    }

    /**
     * @param field
     * @param value
     * @return
     */
    public MongoUpdate addToSet(String field, Object value) {
        update.addToSet(field).each(value);
        return this;
    }

    /**
     * @param field
     * @param values
     * @return
     */
    public MongoUpdate addToSetAll(String field, Collection<?> values) {
        update.addToSet(field).each(values);
        return this;
    }

}
