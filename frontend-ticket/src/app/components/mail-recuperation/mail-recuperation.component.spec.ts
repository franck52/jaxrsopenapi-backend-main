import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MailRecuperationComponent } from './mail-recuperation.component';

describe('MailRecuperationComponent', () => {
  let component: MailRecuperationComponent;
  let fixture: ComponentFixture<MailRecuperationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MailRecuperationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MailRecuperationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
