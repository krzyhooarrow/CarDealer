import { Component, OnInit, Input } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { AuthService } from '../services/auth.service';
import { SubscriptionService } from '../services/subscription.service';



@Component({
  selector: 'app-transaction-user-details',
  templateUrl: './transaction-user-details.component.html',
  styleUrls: ['./transaction-user-details.component.scss']
})
export class TransactionUserDetailsComponent implements OnInit {

  public isLogged:boolean = false;
  public isSubscribing:boolean = true;
  public watchers:number;
  public subscribedList:number[];
  public tryingToSub = false;

  @Input() transaction:TransactionDTO;
  
  constructor(public subs:SubscriptionService,public auth:AuthService) { this.isLogged = auth.isAuthenticated;  auth.authenticationCheck() }

  ngOnInit(): void {
    if(this.transaction)  this.subs.getOfferPopularity(this.transaction.id).subscribe((watchers:number)=> this.watchers = watchers);  
    if(this.isLogged)  this.subs.getUserSubscriptions().subscribe( (list : number[]) => {this.subscribedList = list } );
  }

  sub_or_unsub(){   

    this.tryingToSub = true;

    if (!(this.subscribedList.includes(this.transaction.id)))

    this.subs.subscribe(this.transaction.id).subscribe(
      ()=>{this.subscribedList.push(this.transaction.id); this.tryingToSub = false;},
      ()=>this.subscribedList.push(this.transaction.id)
    );

    else

    this.subs.unsubscribe(this.transaction.id).subscribe(
      ()=>{this.subscribedList = this.subscribedList.filter(value => value != this.transaction.id); this.tryingToSub = false;},
      ()=>this.subscribedList = this.subscribedList.filter(value => value != this.transaction.id)
    );
   
  }



}
