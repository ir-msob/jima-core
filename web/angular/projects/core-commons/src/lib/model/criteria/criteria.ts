import {Filter} from "../filter/filter";
import {BaseModel} from "../base-model";
import {BaseType} from "../dto/base-type";

export namespace Criteria {
  export interface BaseCriteria<ID> extends BaseModel, BaseType {

    get includes(): string[];

    set includes(includes: string[]);

    get includesLimitation(): string[];

    set includesLimitation(includes: string[]);

    get id(): Filter<ID> | undefined;

    set id(id: Filter<ID> | undefined);

  }

  export abstract class BaseCriteriaAbstract<ID> implements BaseCriteria<ID> {

    private _includes: string[] = [];

    get includes(): string[] {
      return this._includes;
    }

    set includes(includes: string[]) {
      this._includes = includes;
    }

    private _includesLimitation: string[] = [];

    get includesLimitation(): string[] {
      return this._includesLimitation;
    }

    set includesLimitation(includesLimitation: string[]) {
      this._includesLimitation = includesLimitation;
    }

    private _id: Filter<ID> | undefined;

    get id(): Filter<ID> | undefined {
      return this._id;
    }

    set id(id: Filter<ID> | undefined) {
      this._id = id;
    }
  }

  export enum FN {
    includes = 'includes',
    includesLimitation = 'includesLimitation',
    id = 'id'
  }
}
