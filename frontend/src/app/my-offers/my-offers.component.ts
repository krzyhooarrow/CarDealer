import { Component, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { AuthService } from '../services/auth.service';
import { SubscriptionService } from '../services/subscription.service';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-my-offers',
  templateUrl: './my-offers.component.html',
  styleUrls: ['./my-offers.component.scss']
})
export class MyOffersComponent implements OnInit {

  constructor(public auth:AuthService) { }

  ngOnInit(): void { }

}
