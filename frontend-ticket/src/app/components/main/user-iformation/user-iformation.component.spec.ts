import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserIformationComponent } from './user-iformation.component';

describe('UserIformationComponent', () => {
  let component: UserIformationComponent;
  let fixture: ComponentFixture<UserIformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserIformationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserIformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
