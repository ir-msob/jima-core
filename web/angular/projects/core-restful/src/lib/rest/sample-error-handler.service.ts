import {BaseErrorHandlerService} from "./base-error-handler.service";

/**
 * SampleErrorHandlerService class.
 * It extends the BaseErrorHandlerService class and provides a sample implementation of an error handler.
 * @class
 * @extends {BaseErrorHandlerService}
 */
export class SampleErrorHandlerService extends BaseErrorHandlerService {
  /**
   * Constructor for the SampleErrorHandlerService class.
   * @param {HttpClient} http - The HttpClient instance.
   */
  constructor(http: HttpClient) {
    super(http, 'api/');
  }

  /**
   * Method to handle errors.
   * This is a sample implementation and does nothing.
   * @param {any} error - The error to handle.
   */
  handleError(error: any): void {
    // Nothing
  }
}
