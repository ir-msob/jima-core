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

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ChannelInfoAbstract<ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> implements BaseModel {
    private Map<String, Serializable> metadata = new HashMap<>();
    private USER user;
    private String callback;
    private String errorCallback;

    public String getErrorCallback() {
        if (Strings.isNotBlank(errorCallback)) {
            return errorCallback;
        } else {
            return callback;
        }
    }

    public enum FN {
        metadata, user, callback, errorCallback
    }
}
