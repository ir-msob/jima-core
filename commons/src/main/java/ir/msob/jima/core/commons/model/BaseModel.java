package ir.msob.jima.core.commons.model;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

/**
 * This interface represents a base model that can be serialized and copied.
 * It provides a default method to create a copy of the implementing class.
 */
public interface BaseModel extends Serializable {

    /**
     * Creates a copy of the implementing class.
     *
     * @param <T> the type of the implementing class
     * @return a copy of the implementing class
     */
    default <T extends BaseModel> T copy() {
        @SuppressWarnings("unchecked")
        T copy = (T) SerializationUtils.clone(this);
        return copy;
    }

}