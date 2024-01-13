import {NullableString} from "../../constants";
import {Domain} from "../domain/domain";
import {Dto} from "./dto";
import BaseDomainAbstract = Domain.BaseDomainAbstract;
import BaseDto = Dto.BaseDto;

export class SampleDto<ID> extends BaseDomainAbstract<ID> implements BaseDto<ID> {
  get classType(): NullableString {
    return (<any>this).constructor.name;
  }
}
