import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { SubscriptionService } from '../services/subscription.service';
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

  constructor(public transactionService:TransactionService,public subs:SubscriptionService,public router:Router) {
    this.transactionService.getTransactionById(+this.router.url.replace('/offer/',''))
    .subscribe(
      ((t:TransactionDTO) => {
        this.transaction = t;
        this.subs.sendOfferVisitedPost(t.id)
      }));
  }

  ngOnInit(): void {
   
  }

}
