import { Component, Input, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transaction-scrapped-data',
  templateUrl: './transaction-scrapped-data.component.html',
  styleUrls: ['./transaction-scrapped-data.component.scss']
})
export class TransactionScrappedDataComponent implements OnInit {

  @Input() transaction:TransactionDTO;

  data;

  constructor(public transactionService:TransactionService) { }

  ngOnInit(): void { this.getScrappedOffers(this.transaction.id)}


  getScrappedOffers(offerId:number){
    this.transactionService.getScrappedOffers(offerId).subscribe((values) => { this.data=values},()=>{});
  }

}
