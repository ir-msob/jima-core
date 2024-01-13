import {Pageable} from "./pageable";
import {Sort} from "./sort";
import {NullableBoolean, NullableNumber, NullablePageable, NullableSort, NullableTs} from "../../constants";

export class Page<T> {
  private _content: NullableTs<T> = [];

  get content(): NullableTs<T> {
    return this._content;
  }

  set content(value: NullableTs<T>) {
    this._content = value;
  }

  private _empty: NullableBoolean;

  get empty(): NullableBoolean {
    return this._empty;
  }

  set empty(value: NullableBoolean) {
    this._empty = value;
  }

  private _first: NullableBoolean;

  get first(): NullableBoolean {
    return this._first;
  }

  set first(value: NullableBoolean) {
    this._first = value;
  }

  private _last: NullableBoolean;

  get last(): NullableBoolean {
    return this._last;
  }

  set last(value: NullableBoolean) {
    this._last = value;
  }

  private _number: NullableNumber;

  get number(): NullableNumber {
    return this._number;
  }

  set number(value: NullableNumber) {
    this._number = value;
  }

  private _numberOfElements: NullableNumber;

  get numberOfElements(): NullableNumber {
    return this._numberOfElements;
  }

  set numberOfElements(value: NullableNumber) {
    this._numberOfElements = value;
  }

  private _size: NullableNumber;

  get size(): NullableNumber {
    return this._size;
  }

  set size(value: NullableNumber) {
    this._size = value;
  }

  private _pageable: NullablePageable;

  get pageable(): Pageable | null | undefined {
    return this._pageable;
  }

  set pageable(value: Pageable | null | undefined) {
    this._pageable = value;
  }

  private _sort: NullableSort;

  get sort(): Sort | null | undefined {
    return this._sort;
  }

  set sort(value: Sort | null | undefined) {
    this._sort = value;
  }

  private _totalElements: NullableNumber;

  get totalElements(): NullableNumber {
    return this._totalElements;
  }

  set totalElements(value: NullableNumber) {
    this._totalElements = value;
  }

  private _totalPages: NullableNumber;

  get totalPages(): NullableNumber {
    return this._totalPages;
  }

  set totalPages(value: NullableNumber) {
    this._totalPages = value;
  }
}

