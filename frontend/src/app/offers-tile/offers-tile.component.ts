import { Component, Input, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { SubscriptionService } from '../services/subscription.service';

@Component({
  selector: 'app-offers-tile',
  templateUrl: './offers-tile.component.html',
  styleUrls: ['./offers-tile.component.scss']
})
export class OffersTileComponent implements OnInit {

  @Input() transactions:Array<TransactionDTO>;
  @Input() results:number;
  subscriptions:number[];

  no_img="https://sertame.com/wp-content/uploads/2019/11/No-Product-Image.png"

  constructor(public subs: SubscriptionService) { }

  ngOnInit(): void {   this.subs.getUserSubscriptions().subscribe( (list : number[]) => {this.subscriptions = list; }); }

}
export interface OfferTile {
  color: string;
  cols: number;
  rows: number;
  text: string;
  isSlider?: boolean;
  visible?:boolean;
}