import {FilterQuery} from "./filter-query";

export class Filter<TYPE> extends FilterQuery<TYPE> {
  private _or: FilterQuery<TYPE> = new FilterQuery<TYPE>();

  get or(): FilterQuery<TYPE> {
    return this._or;
  }

  set or(value: FilterQuery<TYPE>) {
    this._or = value;
  }


  public static or<TYPE>(value: FilterQuery<TYPE>): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.or = value;
    return f;
  }
}
