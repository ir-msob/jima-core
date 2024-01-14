import {FilterQuery} from "./filter-query";

/**
 * Filter class that extends the FilterQuery class.
 * @class
 * @extends {FilterQuery<TYPE>}
 * @template TYPE
 */
export class Filter<TYPE> extends FilterQuery<TYPE> {
  private _or: FilterQuery<TYPE> = new FilterQuery<TYPE>();

  /**
   * Getter for 'or'.
   * @returns {FilterQuery<TYPE>} The 'or' FilterQuery.
   */
  get or(): FilterQuery<TYPE> {
    return this._or;
  }

  /**
   * Setter for 'or'.
   * @param {FilterQuery<TYPE>} value - The 'or' FilterQuery.
   */
  set or(value: FilterQuery<TYPE>) {
    this._or = value;
  }

  /**
   * Static method 'or'.
   * @param {FilterQuery<TYPE>} value - The 'or' FilterQuery.
   * @returns {Filter<TYPE>} A new Filter instance with 'or' set to the provided value.
   */
  public static or<TYPE>(value: FilterQuery<TYPE>): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.or = value;
    return f;
  }
}
