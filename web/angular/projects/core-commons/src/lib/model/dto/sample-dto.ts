import {NullableString} from "../../constants";
import {Domain} from "../domain/domain";
import {Dto} from "./dto";
import BaseDomainAbstract = Domain.BaseDomainAbstract;
import BaseDto = Dto.BaseDto;

/**
 * SampleDto class that extends the BaseDomainAbstract class and implements the BaseDto interface.
 * @class
 * @extends {BaseDomainAbstract<ID>}
 * @implements {BaseDto<ID>}
 * @template ID
 */
export class SampleDto<ID> extends BaseDomainAbstract<ID> implements BaseDto<ID> {
  /**
   * Getter for classType.
   * @returns {NullableString} The name of the constructor.
   */
  get classType(): NullableString {
    return (<any>this).constructor.name;
  }
}
