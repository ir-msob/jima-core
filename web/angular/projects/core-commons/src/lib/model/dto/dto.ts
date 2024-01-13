import {NullableString} from "../../constants";
import {Domain} from "../domain/domain";

export namespace Dto {
  import BaseDomain = Domain.BaseDomain;

  export interface BaseDto<ID> extends BaseDomain<ID> {
    get classType(): NullableString;
  }
}
