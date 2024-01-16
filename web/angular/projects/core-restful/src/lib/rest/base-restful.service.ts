import {HttpClient, HttpHeaders} from "@angular/common/http";
import {HeaderOperation, RestOptions} from "../model/rest-options";
import {Observable} from "rxjs";
import {DEFAULT_HTTP_HEADERS, TOKEN_CACHE_KEY, TOKEN_HEADER_NAME, TOKEN_PREFIX} from "../constants";
import {NullableString} from "@ir-msob-jima/core-commons/lib/constants";
import {isNotEmpty} from "@ir-msob-jima/core-commons";

export abstract class BaseRestfulService {

  constructor(public http: HttpClient, protected prefixPath: NullableString) {
  }

  /**
   * Perform a GET request with optional REST options.
   * @param suffixPath The optional suffix path for the request.
   * @param options The REST options.
   */
  get(suffixPath: NullableString, options?: RestOptions): Observable<any> {
    return this.http.get(this.getUrl(suffixPath), this.getOption(options));
  }

  /**
   * Perform a POST request with optional REST options.
   * @param suffixPath The optional suffix path for the request.
   * @param body The request body.
   * @param options The REST options.
   */
  post(suffixPath: NullableString, body: any, options?: RestOptions): Observable<any> {
    return this.http.post(this.getUrl(suffixPath), body, this.getOption(options));
  }

  /**
   * Perform a PUT request with optional REST options.
   * @param suffixPath The optional suffix path for the request.
   * @param body The request body.
   * @param options The REST options.
   */
  put(suffixPath: NullableString, body: any, options?: RestOptions): Observable<any> {
    return this.http.put(this.getUrl(suffixPath), body, this.getOption(options));
  }

  /**
   * Perform a PATCH request with optional REST options.
   * @param suffixPath The optional suffix path for the request.
   * @param body The request body.
   * @param options The REST options.
   */
  patch(suffixPath: NullableString, body: any, options?: RestOptions): Observable<any> {
    return this.http.patch(this.getUrl(suffixPath), body, this.getOption(options));
  }

  /**
   * Perform a DELETE request with optional REST options.
   * @param suffixPath The optional suffix path for the request.
   * @param options The REST options.
   */
  del(suffixPath: NullableString, options?: RestOptions): Observable<any> {
    return this.http.delete(this.getUrl(suffixPath), this.getOption(options));
  }

  /**
   * Retrieve a token from local storage using a token key.
   * @param tokenKey The token key.
   */
  protected getToken(tokenKey: string): NullableString {
    return localStorage.getItem(tokenKey);
  }

  /**
   * Get the URL for the request.
   * @param suffixPath The optional suffix path for the request.
   * @private
   */
  private getUrl(suffixPath: NullableString): string {
    return isNotEmpty(this.prefixPath) ? this.prefixPath + (suffixPath ?? '') : (suffixPath ?? '');
  }

  /**
   * Generate HTTP service options based on REST options.
   * @param options The REST options.
   * @private
   */
  private getOption(options?: RestOptions): any {
    if (!isNotEmpty(options)) {
      options = RestOptions.newInstance();
    }
    return {
      headers: this.getHeader(options),
      params: isNotEmpty(options.params) ? options.params : null
    };
  }

  /**
   * Get service headers including the token.
   * @param options The REST options.
   * @private
   */
  private getHeader(options: RestOptions): HttpHeaders {
    let result: HttpHeaders = this.prepareHeader(options);
    return this.prepareToken(options, result);
  }

  /**
   * Prepare headers based on the specified header operation.
   * @param options The REST options.
   * @private
   */
  private prepareHeader(options: RestOptions): HttpHeaders {
    let result: HttpHeaders = DEFAULT_HTTP_HEADERS;

    if (isNotEmpty(options.headers) && isNotEmpty(options.headerOperation)) {

      if (options.headerOperation === HeaderOperation.REPLACE) {
        result = options.headers;
      } else if (options.headerOperation === HeaderOperation.APPEND) {
        // Append headers to default headers.
        for (const key of options.headers.keys()) {
          const value: string[] | null = options.headers.getAll(key);
          if (value !== null) {
            result = result.append(key, value);
          }
        }
      }
    }
    return result;
  }

  /**
   * Prepare headers by adding a token if needed.
   * @param options The REST options.
   * @param result The HttpHeaders object.
   * @private
   */
  private prepareToken(options: RestOptions, result: HttpHeaders) {
    // Check if a token is needed and add it to the headers.
    if (isNotEmpty(options.needToken) && options.needToken) {
      if (isNotEmpty(options.tokenKey)) {
        result = result.set(TOKEN_HEADER_NAME, TOKEN_PREFIX + localStorage.getItem(options.tokenKey));
      } else {
        result = result.set(TOKEN_HEADER_NAME, TOKEN_PREFIX + localStorage.getItem(TOKEN_CACHE_KEY));
      }
    }
    return result;
  }
}
