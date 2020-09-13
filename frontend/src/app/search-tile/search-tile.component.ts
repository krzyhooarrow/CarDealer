import { Component, OnInit } from '@angular/core';
import { fail } from 'assert';

@Component({
  selector: 'app-search-tile',
  templateUrl: './search-tile.component.html',
  styleUrls: ['./search-tile.component.scss']
})
export class SearchTileComponent implements OnInit {

  constructor() { }

  tileValues:Array<String> = []
  rowSize = 2;
  selectSize = 20;
  bigSelectSize= 40;
  selectColor = 'lavender';
  separatorColor = 'white'
  // #FCF9FF
  // #C7B6DC
  
  tiles: SearchTile[] = [
    {cols: 20, rows: 3*this.rowSize, color: this.separatorColor},
    {cols: 1, rows: this.bigSelectSize*this.rowSize, color: this.separatorColor},
    {text: 'Car type', cols: 3, rows: this.bigSelectSize*this.rowSize, color: this.selectColor, visible:true,values : ["1","2"]},
    {text: 'Car model', cols: 2, rows: this.selectSize*this.rowSize, color: this.selectColor, visible:true, values : ["1","2"]},
    {text: 'Car mark', cols: 2, rows:  this.selectSize*this.rowSize, color: this.selectColor, visible:true, values : ["1","2"]},
    {text: 'Production year (from)', cols: 2, rows:  this.selectSize*this.rowSize, color: this.selectColor, visible:true, values : ["1","2"]},
    {text: 'Production year (to)', cols: 2, rows:  this.selectSize*this.rowSize, color: this.selectColor, visible:true, values : ["1","2"]},
    {text: 'Fuel type', cols: 2, rows:  this.selectSize*this.rowSize, color: this.selectColor, visible:true, values : ["1","2"]},
    {text: 'Production country', cols: 2, rows:  this.selectSize*this.rowSize, color:this.selectColor, visible:true, values : ["1","2"]},
    {text: 'State', cols: 3, rows: this.bigSelectSize*this.rowSize, color: this.selectColor, visible:true, values : ["New","Used"]},
    {cols: 1, rows: this.bigSelectSize*this.rowSize, color: this.separatorColor},
    {text: 'Location (city)',cols: 2, rows:  this.selectSize*this.rowSize, color:this.selectColor, visible:true, values : ["1","2"]},
    {text: 'Location (country)',cols: 2, rows:  this.selectSize*this.rowSize, color:this.selectColor, visible:true, values : ["1","2"]},
    {text: 'Price (from)', cols: 2, rows:  this.selectSize*this.rowSize, color:this.selectColor, visible:true, values : ["1","2"]},
    {text: 'Price (to)', cols: 2, rows:  this.selectSize*this.rowSize, color:this.selectColor, visible:true, values : ["1","2"]},
    {text: 'Mileage (from)', cols: 2, rows:  this.selectSize*this.rowSize, color: this.selectColor, visible:true, values : ["1","2"]},
    {text: 'Mileage (to)', cols: 2, rows:  this.selectSize*this.rowSize, color: this.selectColor, visible:true, values : ["1","2"]},
    {cols: 5, rows: 12*this.rowSize, color: this.separatorColor },
    {cols: 10, rows: 12*this.rowSize, color: this.separatorColor },
    {cols: 5, rows: 12*this.rowSize, color: this.separatorColor},
    {cols: 20, rows: 1, color: this.separatorColor},
  ];

  ngOnInit(): void { }

}


export interface SearchTile {
  color: string;
  cols: number;
  rows: number;
  text?: string;
  visible?:boolean;
  values?:Array<String>
}

