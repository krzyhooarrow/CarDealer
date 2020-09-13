import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionPriceComponent } from './transaction-price.component';

describe('TransactionPriceComponent', () => {
  let component: TransactionPriceComponent;
  let fixture: ComponentFixture<TransactionPriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransactionPriceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
