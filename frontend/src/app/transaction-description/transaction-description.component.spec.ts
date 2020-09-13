import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionDescriptionComponent } from './transaction-description.component';

describe('TransactionDescriptionComponent', () => {
  let component: TransactionDescriptionComponent;
  let fixture: ComponentFixture<TransactionDescriptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransactionDescriptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
