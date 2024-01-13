package ir.msob.jima.core.commons.model;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

/**
 * @author Yaqub Abdi
 */
public interface BaseModel extends Serializable {

    default <T extends BaseModel> T copy() {
        return (T) SerializationUtils.clone(this);
    }

}
