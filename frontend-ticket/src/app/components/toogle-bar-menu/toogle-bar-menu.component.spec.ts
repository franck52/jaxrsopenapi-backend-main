import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToogleBarMenuComponent } from './toogle-bar-menu.component';

describe('ToogleBarMenuComponent', () => {
  let component: ToogleBarMenuComponent;
  let fixture: ComponentFixture<ToogleBarMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ToogleBarMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ToogleBarMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
