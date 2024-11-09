import { TestBed } from '@angular/core/testing';

import { EmailVericationService } from './email-verication.service';

describe('EmailVericationService', () => {
  let service: EmailVericationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmailVericationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
