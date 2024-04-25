package ir.msob.jima.core.commons.model.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.dto.BaseType;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.security.BaseUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The 'ChannelMessage' class represents a message that is sent through a channel.
 * It extends the 'ChannelInfoAbstract' class and implements the 'BaseType' interface.
 * The class includes fields for data and status, and getter and setter methods for these fields.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor.
 * The class is parameterized with three types 'ID' that extends 'Comparable' and 'Serializable', 'USER' that extends 'BaseUser', and 'DATA' that extends 'ModelType'.
 * The 'FN' enum represents the field names of the class.
 *
 * @param <USER> The type of user.
 * @param <DATA> The type of data.
 */
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelMessage<USER extends BaseUser, DATA extends ModelType> extends ChannelInfoAbstract<USER> implements BaseType {
    /**
     * The data of the channel message.
     */
    private DATA data;

    /**
     * The status of the channel message.
     */
    private Integer status;

    /**
     * The 'FN' enum represents the field names of the class.
     */
    public enum FN {
        data, status
    }
}