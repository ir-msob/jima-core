import {BaseRestfulService} from "./base-restful.service";
import {RestOptions} from "../model/rest-options";
import {BehaviorSubject, Observable} from "rxjs";
import {NullableString} from "@ir-msob-framework/core-commons/lib/constants";

/**
 * A base error handler service that extends the BaseRestfulService class. This class provides methods for making various RESTful requests with built-in error handling.
 */
export abstract class BaseErrorHandlerService extends BaseRestfulService {

  /**
   * Make a GET request with error handling.
   * @param suffixPath The optional suffix path for the request.
   * @param options Additional REST options.
   * @returns An observable that emits the response or error.
   */
  getWithErrors(suffixPath: NullableString, options?: RestOptions): Observable<any> {
    return this.createObservableForRequest(this.get.bind(this), suffixPath, undefined, options);
  }

  /**
   * Make a POST request with error handling.
   * @param suffixPath The optional suffix path for the request.
   * @param body The request body.
   * @param options Additional REST options.
   * @returns An observable that emits the response or error.
   */
  postWithErrors(suffixPath: NullableString, requestBody: any, options?: RestOptions): Observable<any> {
    return this.createObservableForRequest(this.post.bind(this), suffixPath, requestBody, options);
  }

  /**
   * Make a PUT request with error handling.
   * @param suffixPath The optional suffix path for the request.
   * @param body The request body.
   * @param options Additional REST options.
   * @returns An observable that emits the response or error.
   */
  putWithErrors(suffixPath: NullableString, requestBody: any, options?: RestOptions): Observable<any> {
    return this.createObservableForRequest(this.put.bind(this), suffixPath, requestBody, options);
  }

  /**
   * Make a PATCH request with error handling.
   * @param suffixPath The optional suffix path for the request.
   * @param body The request body.
   * @param options Additional REST options.
   * @returns An observable that emits the response or error.
   */
  patchWithErrors(suffixPath: NullableString, requestBody: any, options?: RestOptions): Observable<any> {
    return this.createObservableForRequest(this.patch.bind(this), suffixPath, requestBody, options);
  }

  /**
   * Make a DELETE request with error handling.
   * @param suffixPath The optional suffix path for the request.
   * @param options Additional REST options.
   * @returns An observable that emits the response or error.
   */
  deleteWithErrors(suffixPath: NullableString, options?: RestOptions): Observable<any> {
    return this.createObservableForRequest(this.del.bind(this), suffixPath, undefined, options);
  }

  /**
   * Abstract method to handle errors, which should be implemented in subclasses.
   * @param error The error object.
   */
  abstract handleError(error: any): void;

  /**
   * Create an observable for a RESTful request with error handling.
   * @param requestFunction The RESTful request function (e.g., get, post, put, patch, del).
   * @param suffixPath The optional suffix path for the request.
   * @param body The request body (if applicable).
   * @param options Additional REST options.
   * @returns An observable that emits the response or error.
   */
  private createObservableForRequest(requestFunction: Function, suffixPath: NullableString, body?: any, options?: RestOptions): Observable<any> {
    const result: BehaviorSubject<any> = new BehaviorSubject<any>(null);

    requestFunction(suffixPath, body, options)
      .subscribe({
        next: (response: any) => {
          if (response) {
            result.next(response);
          }
        },
        error: (error: any) => {
          this.handleError(error);
          result.next(error);
        }
      });

    return result.asObservable();
  }
}
