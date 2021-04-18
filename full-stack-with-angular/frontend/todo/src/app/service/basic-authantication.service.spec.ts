import { TestBed } from '@angular/core/testing';

import { BasicAuthanticationService } from './basic-authantication.service';

describe('BasicAuthanticationService', () => {
  let service: BasicAuthanticationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BasicAuthanticationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
