import {BaseErrorHandlerService} from "./base-error-handler.service";
import {HttpClient} from "@angular/common/http";


export class SampleErrorHandlerService extends BaseErrorHandlerService {
  constructor(http: HttpClient) {
    super(http, 'api/');
  }

  handleError(error: any): void {
    // Nothing
  }

}
