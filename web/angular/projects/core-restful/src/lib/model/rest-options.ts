import {HttpHeaders, HttpParams} from "@angular/common/http";
import {NullableString} from "@ir-msob-framework/core-commons/lib/constants";

export class RestOptions {
  /**
   * Token
   */
  needToken?: boolean = true;
  tokenKey?: NullableString;
  /**
   * Params
   */
  params?: HttpParams;
  /**
   * Headers
   */
  headers?: HttpHeaders;
  headerOperation?: HeaderOperation;

  constructor() {
  }

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

export enum HeaderOperation {
  REPLACE,
  APPEND
}
