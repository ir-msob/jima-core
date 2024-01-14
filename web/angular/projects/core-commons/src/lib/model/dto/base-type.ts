import {BaseModel} from "../base-model";
import {NullableString} from "../../constants";

/**
 * BaseType interface that extends the BaseModel interface.
 * @interface
 * @extends {BaseModel}
 *
 */
export interface BaseType extends BaseModel {
  /**
   * Getter for classType.
   * @returns {NullableString} The classType.
   */
  // FIXME: getClassType(): NullableString{ return the class type;}
}
