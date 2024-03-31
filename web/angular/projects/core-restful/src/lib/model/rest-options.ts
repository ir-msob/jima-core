import {HttpHeaders, HttpParams} from "@angular/common/http";
import {NullableString} from "@ir-msob/jima-core-commons/lib/constants";

/**
 * RestOptions class.
 * It provides a set of options for RESTful operations.
 * @class
 */
export class RestOptions {
  /**
   * Indicates whether a token is needed for the operation.
   * @type {boolean}
   */
  needToken?: boolean = true;

  /**
   * The key of the token.
   * @type {NullableString}
   */
  tokenKey?: NullableString;

  /**
   * The parameters for the operation.
   * @type {HttpParams}
   */
  params?: HttpParams;

  /**
   * The headers for the operation.
   * @type {HttpHeaders}
   */
  headers?: HttpHeaders;

  /**
   * The operation to perform on the headers.
   * @type {HeaderOperation}
   */
  headerOperation?: HeaderOperation;

  /**
   * Constructor for the RestOptions class.
   */
  constructor() {
  }

  /**
   * Static method to create a new instance of RestOptions.
   * @returns {RestOptions} A new instance of RestOptions.
   */
  public static newInstance(): RestOptions {
    const ro = new RestOptions();
    ro.needToken = true;
    ro.tokenKey = undefined;
    ro.params = undefined;
    ro.headers = undefined;
    ro.headerOperation = undefined;
    return ro;
  }
}

/**
 * Enum for header operations.
 * @enum
 */
export enum HeaderOperation {
  REPLACE,
  APPEND
}
