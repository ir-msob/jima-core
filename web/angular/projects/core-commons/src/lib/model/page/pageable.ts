import {Sort} from "./sort";
import {NullableBoolean, NullableNumber, NullableSort} from "../../constants";

export class Pageable {
  private _offset: NullableNumber;

  get offset(): NullableNumber {
    return this._offset;
  }

  set offset(value: NullableNumber) {
    this._offset = value;
  }

  private _pageNumber: NullableNumber;

  get pageNumber(): NullableNumber {
    return this._pageNumber;
  }

  set pageNumber(value: NullableNumber) {
    this._pageNumber = value;
  }

  private _pageSize: NullableNumber;

  get pageSize(): NullableNumber {
    return this._pageSize;
  }

  set pageSize(value: NullableNumber) {
    this._pageSize = value;
  }

  private _paged: NullableBoolean;

  get paged(): NullableBoolean {
    return this._paged;
  }

  set paged(value: NullableBoolean) {
    this._paged = value;
  }

  private _sort: NullableSort;

  get sort(): Sort | null | undefined {
    return this._sort;
  }

  set sort(value: Sort | null | undefined) {
    this._sort = value;
  }

  private _unpaged: NullableBoolean;

  get unpaged(): NullableBoolean {
    return this._unpaged;
  }

  set unpaged(value: NullableBoolean) {
    this._unpaged = value;
  }
}
