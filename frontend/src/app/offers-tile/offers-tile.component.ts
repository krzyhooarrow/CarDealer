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
  subscriptions:number[];

  no_images = [ {path: 'https://sertame.com/wp-content/uploads/2019/11/No-Product-Image.png'},
  {path: 'https://sertame.com/wp-content/uploads/2019/11/No-Product-Image.png'},
  ]
  img="https://sertame.com/wp-content/uploads/2019/11/No-Product-Image.png"

  rowSize = 1;
  selectSize = 20;
  bigSelectSize= 40;
  maxCols = 40;
  whiteColor = 'white'
  images = [
    {path: 'https://i.pinimg.com/originals/58/67/49/5867496ec9a6f50592a70f2219203aaf.jpg'},
    {path: 'https://i.pinimg.com/originals/58/67/49/5867496ec9a6f50592a70f2219203aaf.jpg'}
  
]
  
  tiles: OfferTile[] = [

    {text: '', cols: this.maxCols, rows: 1, color: this.whiteColor},
    {text: '', cols: 0.02*this.maxCols, rows: 100/this.rowSize, color: this.whiteColor},
    {text: '', cols: 0.1*this.maxCols, rows: 100/this.rowSize, color: 'lavender', isSlider:true, visible:true},
    {text: '', cols: 0.38*this.maxCols, rows: 100/this.rowSize, color: this.whiteColor, visible:true},
    {text: '', cols: 0.1*this.maxCols, rows: 100/this.rowSize, color: 'lavender', isSlider : true, visible:true},
    {text: '', cols: 0.38*this.maxCols, rows: 100/this.rowSize, color: this.whiteColor, visible:true},
    {text: '', cols: 0.02*this.maxCols, rows: 100/this.rowSize, color: this.whiteColor},
    {text: '', cols: this.maxCols, rows: 1, color: this.whiteColor},
  ];

  constructor(public subs: SubscriptionService) { }

  ngOnInit(): void {   this.subs.getUserSubscriptions().subscribe(
    (list : number[]) => {this.subscriptions = list
    }
  );
 }

}
export interface OfferTile {
  color: string;
  cols: number;
  rows: number;
  text: string;
  isSlider?: boolean;
  visible?:boolean;
}