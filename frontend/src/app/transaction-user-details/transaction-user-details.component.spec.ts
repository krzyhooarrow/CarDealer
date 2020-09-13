import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionUserDetailsComponent } from './transaction-user-details.component';

describe('TransactionUserDetailsComponent', () => {
  let component: TransactionUserDetailsComponent;
  let fixture: ComponentFixture<TransactionUserDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransactionUserDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionUserDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
