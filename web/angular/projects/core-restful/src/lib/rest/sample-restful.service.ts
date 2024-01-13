import {HttpClient} from "@angular/common/http";
import {BaseRestfulService} from "./base-restful.service";

export class SampleRestfulService extends BaseRestfulService {

  constructor(http: HttpClient) {
    super(http, 'api/');
  }

}
