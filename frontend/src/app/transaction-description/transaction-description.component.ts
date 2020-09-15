import { Component, Input, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transaction-description',
  templateUrl: './transaction-description.component.html',
  styleUrls: ['./transaction-description.component.scss']
})
export class TransactionDescriptionComponent implements OnInit {

  @Input() transaction:TransactionDTO;

  public offerDescription:String ;

  constructor() {   }

  ngOnInit(): void { }

}
