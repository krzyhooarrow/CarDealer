import { Component, OnInit, Input } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';



@Component({
  selector: 'app-transaction-user-details',
  templateUrl: './transaction-user-details.component.html',
  styleUrls: ['./transaction-user-details.component.scss']
})
export class TransactionUserDetailsComponent implements OnInit {

  public isLogged:boolean = true;
  public isSubscribing:boolean = true;

  @Input() transaction:TransactionDTO;
  
  constructor() { }

  ngOnInit(): void {
  }

}
