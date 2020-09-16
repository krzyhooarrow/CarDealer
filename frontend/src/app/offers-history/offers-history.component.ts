import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-offers-history',
  templateUrl: './offers-history.component.html',
  styleUrls: ['./offers-history.component.scss']
})
export class OffersHistoryComponent implements OnInit {

  constructor() { }

  @ViewChild(MatSort) sort: MatSort;
  
  displayedColumns: string[] = ['item', 'cost'];
  transactions: Transaction[] = [
    {item: 'Beach ball', cost: 4},
    {item: 'Towel', cost: 5},
    {item: 'Frisbee', cost: 2},
    {item: 'Sunscreen', cost: 4},
    {item: 'Cooler', cost: 25},
    {item: 'Swim suit', cost: 15},
  ];

  sortData(sort: MatSort) {
    const data = this.transactions.slice();
    if (!sort.active || sort.direction === '') {
      this.transactions = data;
      return;
    }

    this.transactions = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'cost': return this.compare(a.cost, b.cost, isAsc);
        default: return 0;
      }
    });
  }
  
  compare(a: number | string, b: number | string, isAsc: boolean) { return (a < b ? -1 : 1) * (isAsc ? 1 : -1);  }

  
  getTotalCost() {
    return this.transactions.map(t => t.cost).reduce((acc, value) => acc + value, 0);
  }

  ngOnInit(): void {
  }

 
}
interface Transaction{
  item:String,
  cost:number
}
