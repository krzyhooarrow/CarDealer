import { Component, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { AuthService } from '../services/auth.service';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-my-offers',
  templateUrl: './my-offers.component.html',
  styleUrls: ['./my-offers.component.scss']
})
export class MyOffersComponent implements OnInit {


  auth:AuthService
  transactionService:TransactionService
  transactions:TransactionDTO[]

  constructor(auth:AuthService,transactionService:TransactionService) {
    this.transactionService = transactionService;
    this.auth = auth 
    auth.authenticationCheck()

    
    this.getTransactions()
  }



  getTransactions(){
    this.transactionService.getAllOffersByQuery(
      {
      "type": "Sedan",
      "model": null,
      "mark": null,
      
      "fuelType": "diesel",

    
      "highPrice":12000,
      "lowPrice": 11000
  }
  ).subscribe((offers: Array<TransactionDTO>) =>   {    this.transactions = offers    } ); 

}

  ngOnInit(): void {
  }

}
