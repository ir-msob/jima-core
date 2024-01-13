import {NullableT} from "../../constants";

export namespace Domain {
  export interface BaseDomain<ID> {
    get domainId(): NullableT<ID>;

    set domainId(id: NullableT<ID>);
  }

  export abstract class BaseDomainAbstract<ID> implements BaseDomain<ID> {
    private _id: NullableT<ID>;

    get id(): NullableT<ID> {
      return this._id;
    }

    set id(value: NullableT<ID>) {
      this._id = value;
    }

    get domainId(): NullableT<ID> {
      return this._id;
    }

    set domainId(id: NullableT<ID>) {
      this._id = id;
    }
  }

  export enum FN {
    includes = 'includes',
    includesLimitation = 'includesLimitation',
    id = 'id'
  }
}
