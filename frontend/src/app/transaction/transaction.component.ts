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

no_img="https://sertame.com/wp-content/uploads/2019/11/No-Product-Image.png"

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
