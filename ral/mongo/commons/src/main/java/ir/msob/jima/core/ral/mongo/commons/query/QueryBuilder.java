package ir.msob.jima.core.ral.mongo.commons.query;

import com.google.common.base.Strings;
import ir.msob.jima.core.ral.mongo.commons.criteria.MongoCriteria;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * A query builder for constructing complex MongoDB queries.
 * This class provides methods to build MongoDB queries with regular expression criteria.
 */
public class QueryBuilder extends MongoQuery<QueryBuilder> {

    /**
     * Creates a new QueryBuilder instance.
     */
    public QueryBuilder() {
        super();
    }

    /**
     * Create a new QueryBuilder instance.
     *
     * @return A new QueryBuilder instance.
     */
    public static QueryBuilder builder() {
        return new QueryBuilder();
    }

    /**
     * This method returns the current instance of the QueryBuilder, which is used for method chaining.
     *
     * @return The current QueryBuilder instance.
     */
    @Override
    protected QueryBuilder build() {
        return this;
    }

    /**
     * Build an "OR" query with regular expression criteria for multiple fields.
     *
     * @param value     The regular expression value to match.
     * @param fieldList The fields to apply regular expression criteria on.
     * @return The QueryBuilder instance for method chaining.
     */
    public QueryBuilder regexOrOperator(String value, Object... fieldList) {
        if (!Strings.isNullOrEmpty(value) && fieldList != null && fieldList.length > 0) {
            List<Criteria> criteriaList = new ArrayList<>();
            for (Object field : fieldList) {
                criteriaList.add(MongoCriteria.regex(field, value));
            }
            this.orOperator(criteriaList);
        }
        return this;
    }
}
