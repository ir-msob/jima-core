import {of, throwError} from 'rxjs';
import {SampleErrorHandlerService} from "./sample-error-handler.service";

describe('BaseErrorHandlerService', () => {
  let service: SampleErrorHandlerService;
  let http: any;

  beforeEach(() => {
    http = jasmine.createSpyObj('HttpClient', ['get', 'post', 'put', 'patch', 'delete']);
    service = new SampleErrorHandlerService(http);
  });

  it('should be created', () => {
    // Ensure that the service is created successfully
    expect(service).toBeTruthy();
  });

  it('getWithErrors should return data when successful', (done) => {
    const data = 'Mock GET Response';
    // Mock the HttpClient's get method to return data
    http.get.and.returnValue(of(data));

    // Test that the getWithErrors method returns the expected data
    service.getWithErrors('data').subscribe((response) => {
      expect(response).toBe(data);
      done();
    });
  });

  xit('getWithErrors should handle error', (done) => {
    const error = 'Mock Error Response';
    spyOn(service, 'handleError');
    // Mock the HttpClient's get method to throw an error
    http.get.and.returnValue(throwError(() => error));

    // Test that the getWithErrors method handles the error and calls the handleError method
    service.getWithErrors('error').subscribe({
      next: (response) => {
        // Ensure that the response is the error
        expect(response).toBe(error);
      },
      error: (err) => {
        // Ensure that the error matches the expected error
        expect(err).toBe(error);
        // Ensure that the handleError method is called with the error
        expect(service.handleError).toHaveBeenCalledWith(error);
        done();
      },
    });
  });

  it('postWithErrors should return data when successful', (done) => {
    const data = 'Mock POST Response';
    // Mock the HttpClient's post method to return data
    http.post.and.returnValue(of(data));

    // Test that the postWithErrors method returns the expected data
    service.postWithErrors('data', {}).subscribe((response) => {
      expect(response).toBe(data);
      done();
    });
  });

  it('putWithErrors should return data when successful', (done) => {
    const data = 'Mock PUT Response';
    // Mock the HttpClient's put method to return data
    http.put.and.returnValue(of(data));

    // Test that the putWithErrors method returns the expected data
    service.putWithErrors('data', {}).subscribe((response) => {
      expect(response).toBe(data);
      done();
    });
  });

  it('patchWithErrors should return data when successful', (done) => {
    const data = 'Mock PATCH Response';
    // Mock the HttpClient's patch method to return data
    http.patch.and.returnValue(of(data));

    // Test that the patchWithErrors method returns the expected data
    service.patchWithErrors('data', {}).subscribe((response) => {
      expect(response).toBe(data);
      done();
    });
  });

  it('deleteWithErrors should return data when successful', (done) => {
    const data = 'Mock DELETE Response';
    // Mock the HttpClient's delete method to return data
    http.delete.and.returnValue(of(data));

    // Test that the deleteWithErrors method returns the expected data
    service.deleteWithErrors('data').subscribe((response) => {
      expect(response).toBe(data);
      done();
    });
  });
});
