package ir.msob.jima.core.commons.domain;

import ir.msob.jima.core.commons.element.BaseElement;
import ir.msob.jima.core.commons.shared.BaseModel;

import java.io.Serializable;

/**
 * The {@code BaseDomain} interface represents the basic class for domain models.
 * It extends the {@code BaseModel} interface and implements the {@code Comparable} interface
 * with a generic type {@code BaseDomain<ID>}, enabling domain models to be compared based on their IDs.
 * <p>
 * This interface is generic, with the generic type {@code ID} extending {@code Comparable} and {@code Serializable}.
 * It means that the ID of the domain model can be of any type that is comparable and serializable.
 * <p>
 * The interface includes getter and setter methods for the domain ID and for the domain ID field name.
 * Additionally, it provides a {@code compareTo} method for comparing domain models based on their IDs.
 *
 * @param <ID> The type of the ID of the domain model.
 */
public interface BaseDomain<ID extends Comparable<ID> & Serializable> extends BaseModel, BaseElement<ID> {

}