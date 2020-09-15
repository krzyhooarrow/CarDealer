import { Component, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {

  images = [
    {path: 'https://image.ceneostatic.pl/data/products/87819426/i-bmw-850-bmw-m850i-xdrive-coupe-pakiet-m.jpg'},
    {path: 'https://ireland.apollo.olxcdn.com/v1/files/eyJmbiI6InZsdzV2cHhsYjI0ei1PVE9NT1RPUEwiLCJ3IjpbeyJmbiI6IndnNGducXA2eTFmLU9UT01PVE9QTCIsInMiOiIxNiIsInAiOiIxMCwtMTAiLCJhIjoiMCJ9XX0.dBzuankVkwDu5GBQ_WP297X4MLjjRpWvWmPnmI4Ljqw/image;s=1080x720'},
]

  public transaction:TransactionDTO

  constructor(transactionService:TransactionService) { transactionService.getTransactionById(1).subscribe(((t:TransactionDTO) => {this.transaction = t}));
  }

  ngOnInit(): void {
  }

}
