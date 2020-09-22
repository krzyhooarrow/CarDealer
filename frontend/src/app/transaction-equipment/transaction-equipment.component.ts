import { Component, Input, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';

@Component({
  selector: 'app-transaction-equipment',
  templateUrl: './transaction-equipment.component.html',
  styleUrls: ['./transaction-equipment.component.scss']
})
export class TransactionEquipmentComponent implements OnInit {

  constructor() { }
  
  @Input() transaction:TransactionDTO;

  ngOnInit(): void {
  }

}
