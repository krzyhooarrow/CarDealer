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



  openDetails() {
    // const dialogRef = this.dialog.open(OfferDetailsComponent);
    // dialogRef.afterClosed().subscribe(result => {
    //   console.log(`Dialog result: ${result}`);
    // });
  }

  rowSize = 1;
  selectSize = 20;
  bigSelectSize= 40;
  maxCols = 5;
  whiteColor = 'white'

  tiles: OfferTile[] = [

    {text: '123', cols: 0.1*this.maxCols, rows: 50/this.rowSize, color: 'blue'},
    // {text: '', cols: 0.2*this.maxCols, rows: 50/this.rowSize, color: this.whiteColor, visible:true},
  ];
}
export interface OfferTile {
  color: string;
  cols: number;
  rows: number;
  text: string;
  isSlider?: boolean;
  visible?:boolean;
}