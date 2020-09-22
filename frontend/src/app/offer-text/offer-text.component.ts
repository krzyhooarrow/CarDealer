import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { AuthService } from '../services/auth.service';
import { SubscriptionService } from '../services/subscription.service';


@Component({
  selector: 'app-offer-text',
  templateUrl: './offer-text.component.html',
  styleUrls: ['./offer-text.component.scss']
})
export class OfferTextComponent implements OnInit {

  @Input() transaction: TransactionDTO;
  @Input() subscribedList: number[];

  constructor(public dialog: MatDialog,public auth:AuthService,public subs:SubscriptionService) { }

  ngOnInit(): void { }

  sub_or_unsub(event: any){   

    if(event)
    if (!(this.subscribedList.includes(event)))

    this.subs.subscribe(event).subscribe(
      ()=>this.subscribedList.push(event),
      ()=>this.subscribedList.push(event)
    );

    else

    this.subs.unsubscribe(event).subscribe(
      ()=>this.subscribedList = this.subscribedList.filter(value => value != event),
      ()=>this.subscribedList = this.subscribedList.filter(value => value != event)
    );
   
  }
}