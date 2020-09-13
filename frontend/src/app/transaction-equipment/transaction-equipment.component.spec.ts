import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionEquipmentComponent } from './transaction-equipment.component';

describe('TransactionEquipmentComponent', () => {
  let component: TransactionEquipmentComponent;
  let fixture: ComponentFixture<TransactionEquipmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransactionEquipmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionEquipmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
