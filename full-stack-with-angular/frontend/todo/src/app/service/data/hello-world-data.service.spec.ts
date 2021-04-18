import { TestBed } from '@angular/core/testing';

import { HelloWorldDataService } from './hello-world-data.service';

describe('HelloWorldDataService', () => {
  let service: HelloWorldDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HelloWorldDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
