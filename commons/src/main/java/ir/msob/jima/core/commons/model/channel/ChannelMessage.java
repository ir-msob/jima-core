package ir.msob.jima.core.commons.model.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.dto.BaseType;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.core.commons.security.BaseUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelMessage<ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>, DATA extends ModelType> extends ChannelInfoAbstract<ID, USER> implements BaseType {
    private DATA data;
    private Integer status;

    public enum FN {
        data, status
    }
}
