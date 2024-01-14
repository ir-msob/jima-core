package ir.msob.jima.core.commons.model.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.BaseModel;
import ir.msob.jima.core.commons.security.BaseUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The 'ChannelInfoAbstract' class represents an abstract class for channel information.
 * It implements the 'BaseModel' interface and includes fields for metadata, user, callback, and errorCallback.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor and getter and setter methods for the fields.
 * The class is parameterized with two types 'ID' that extends 'Comparable' and 'Serializable', and 'USER' that extends 'BaseUser'.
 * The 'getErrorCallback' method returns the errorCallback if it is not blank, otherwise it returns the callback.
 * The 'FN' enum represents the field names of the class.
 *
 * @param <ID>   The type of ID.
 * @param <USER> The type of user.
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ChannelInfoAbstract<ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> implements BaseModel {
    /**
     * The metadata of the channel information.
     */
    private Map<String, Serializable> metadata = new HashMap<>();

    /**
     * The user of the channel information.
     */
    private USER user;

    /**
     * The callback of the channel information.
     */
    private String callback;

    /**
     * The errorCallback of the channel information.
     */
    private String errorCallback;

    /**
     * Returns the errorCallback if it is not blank, otherwise returns the callback.
     *
     * @return The errorCallback if it is not blank, otherwise the callback.
     */
    public String getErrorCallback() {
        if (Strings.isNotBlank(errorCallback)) {
            return errorCallback;
        } else {
            return callback;
        }
    }

    /**
     * The 'FN' enum represents the field names of the class.
     */
    public enum FN {
        metadata, user, callback, errorCallback
    }
}