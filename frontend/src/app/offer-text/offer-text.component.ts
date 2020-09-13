import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-offer-text',
  templateUrl: './offer-text.component.html',
  styleUrls: ['./offer-text.component.scss']
})
export class OfferTextComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
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