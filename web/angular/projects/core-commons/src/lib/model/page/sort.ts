import {NullableBoolean} from "../../constants";

export class Sort {
  private _empty: NullableBoolean;

  get empty(): NullableBoolean {
    return this._empty;
  }

  set empty(value: NullableBoolean) {
    this._empty = value;
  }

  private _sorted: NullableBoolean;

  get sorted(): NullableBoolean {
    return this._sorted;
  }

  set sorted(value: NullableBoolean) {
    this._sorted = value;
  }

  private _unsorted: NullableBoolean;

  get unsorted(): NullableBoolean {
    return this._unsorted;
  }

  set unsorted(value: NullableBoolean) {
    this._unsorted = value;
  }

}


