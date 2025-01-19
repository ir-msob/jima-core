package ir.msob.jima.core.ral.mongo.commons.operator;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * Utility class for working with MongoDB Query results.
 * This class provides methods to handle query results and return boolean values.
 */
public class QueryUtil {

    private QueryUtil() {
    }

    /**
     * Converts an UpdateResult to a boolean value indicating whether the update was successful.
     *
     * @param updateResult The UpdateResult to check.
     * @return `true` if the update was successful, `false` otherwise.
     */
    public static Boolean updateResultBoolean(UpdateResult updateResult) {
        if (updateResult == null || updateResult.getMatchedCount() == 0)
            return false;
        if (updateResult.getModifiedCount() == 0)
            return false;
        return updateResult.getModifiedCount() > 0;
    }

    /**
     * Converts a DeleteResult to a boolean value indicating whether the delete operation was successful.
     *
     * @param deleteResult The DeleteResult to check.
     * @return `true` if the delete operation was successful, `false` otherwise.
     */
    public static Boolean deleteResultBoolean(DeleteResult deleteResult) {
        if (deleteResult == null || deleteResult.getDeletedCount() == 0)
            return false;
        return deleteResult.getDeletedCount() > 0;
    }
}
