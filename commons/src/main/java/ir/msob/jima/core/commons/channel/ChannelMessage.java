package ir.msob.jima.core.commons.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.dto.BaseType;
import ir.msob.jima.core.commons.dto.ModelType;
import ir.msob.jima.core.commons.exception.ExceptionResponseAbstract;
import ir.msob.jima.core.commons.security.BaseUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The 'ChannelMessage' class represents a message that is sent through a channel.
 * It extends the 'ChannelInfoAbstract' class and implements the 'BaseType' interface.
 * The class includes fields for data, user, status, channel, and callbacks, along with their respective getter and setter methods.
 * The class uses the 'JsonInclude' annotation to specify that null fields should not be included in the JSON representation of an instance.
 * It also includes a no-argument constructor.
 * The class is parameterized with two types:
 * - 'USER' that extends 'BaseUser',
 * - 'DATA' that extends 'ModelType'.
 * The 'FN' enum represents the field names of the class.
 *
 * @param <USER> The type of user associated with the channel message.
 * @param <DATA> The type of data contained in the channel message.
 *               <p>
 *               This class also implements the 'Serializable' interface to allow instances to be serialized.
 */
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelMessage<USER extends BaseUser, DATA extends ModelType> implements BaseType {
    /**
     * The metadata of the channel information.
     * This map can hold additional information child to the channel message.
     */
    @Builder.Default
    private Map<String, Serializable> metadata = new HashMap<>();

    /**
     * The user of the channel information.
     * This field represents the user associated with the channel message.
     */
    private USER user;

    /**
     * The data of the channel message.
     * This field contains the actual data being sent in the channel message.
     */
    private DATA data;

    /**
     * The status of the channel message.
     * This field indicates the current status of the message (e.g., sent, received, read).
     */
    private Integer status;

    /**
     * The channel of the callback.
     * This field specifies the channel through which the callback will be sent.
     */
    private String channel;

    /**
     * A list of callbacks associated with the channel message.
     * This list can hold multiple ChannelMessage instances that are callbacks.
     */
    @Singular
    private List<ChannelMessage<USER, ? extends ModelType>> callbacks;

    /**
     * A list of error callbacks associated with the channel message.
     * This list can hold multiple ChannelMessage instances that represent error callbacks.
     */
    @Singular
    private List<ChannelMessage<USER, ? extends ExceptionResponseAbstract>> errorCallbacks;

    /**
     * The 'FN' enum represents the field names of the class.
     * This enum can be used to reference the fields in a type-safe manner.
     */
    public enum FN {
        data, status, metadata, user, channel, callbacks, errorCallbacks
    }
}