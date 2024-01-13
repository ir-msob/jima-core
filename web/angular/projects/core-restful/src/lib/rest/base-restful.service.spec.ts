import {BaseRestfulService} from './base-restful.service';
import {Observable, of} from 'rxjs';
import {SampleRestfulService} from "./sample-restful.service";

// MockHttpClient simulates HTTP requests for testing
class MockHttpClient {
  get(url: string, options?: any): Observable<any> {
    // Simulate a GET request response
    return of('Mock GET Response');
  }

  post(url: string, body: any, options?: any): Observable<any> {
    // Simulate a POST request response
    return of('Mock POST Response');
  }

  put(url: string, body: any, options?: any): Observable<any> {
    // Simulate a PUT request response
    return of('Mock PUT Response');
  }

  patch(url: string, body: any, options?: any): Observable<any> {
    // Simulate a PATCH request response
    return of('Mock PATCH Response');
  }

  delete(url: string, options?: any): Observable<any> {
    // Simulate a DELETE request response
    return of('Mock DELETE Response');
  }
}

describe('BaseRestfulService', () => {
  let service: BaseRestfulService;

  beforeEach(() => {
    // Create a MockHttpClient for testing
    const http = new MockHttpClient() as any;
    // Instantiate the service under test (SampleRestfulService)
    service = new SampleRestfulService(http);
  });

  it('should be created', () => {
    // Ensure that the service is created
    expect(service).toBeTruthy();
  });

  it('get should make a GET request', (done) => {
    // Test the GET method
    service.get('data').subscribe((response) => {
      // Check if the response is as expected
      expect(response).toBe('Mock GET Response');
      done();
    });
  });

  it('post should make a POST request', (done) => {
    // Test the POST method
    service.post('data', {}).subscribe((response) => {
      // Check if the response is as expected
      expect(response).toBe('Mock POST Response');
      done();
    });
  });

  it('put should make a PUT request', (done) => {
    // Test the PUT method
    service.put('data', {}).subscribe((response) => {
      // Check if the response is as expected
      expect(response).toBe('Mock PUT Response');
      done();
    });
  });

  it('patch should make a PATCH request', (done) => {
    // Test the PATCH method
    service.patch('data', {}).subscribe((response) => {
      // Check if the response is as expected
      expect(response).toBe('Mock PATCH Response');
      done();
    });
  });

  it('del should make a DELETE request', (done) => {
    // Test the DELETE method
    service.del('data').subscribe((response) => {
      // Check if the response is as expected
      expect(response).toBe('Mock DELETE Response');
      done();
    });
  });
});
