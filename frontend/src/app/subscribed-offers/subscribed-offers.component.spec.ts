import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribedOffersComponent } from './subscribed-offers.component';

describe('SubscribedOffersComponent', () => {
  let component: SubscribedOffersComponent;
  let fixture: ComponentFixture<SubscribedOffersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubscribedOffersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscribedOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
