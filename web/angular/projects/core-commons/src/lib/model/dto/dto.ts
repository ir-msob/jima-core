import {NullableString} from "../../constants";
import {Domain} from "../domain/domain";

/**
 * Namespace for Dto.
 * It contains the BaseDto interface which extends the BaseDomain interface.
 */
export namespace Dto {
  import BaseDomain = Domain.BaseDomain;

  /**
   * Interface for BaseDto.
   * It extends the BaseDomain interface.
   * It provides a getter method for classType.
   * @interface
   * @extends {BaseDomain<ID>}
   * @template ID
   */
  export interface BaseDto<ID> extends BaseDomain<ID> {
    /**
     * Getter for classType.
     * @returns {NullableString} The classType.
     */
    get classType(): NullableString;
  }
}
