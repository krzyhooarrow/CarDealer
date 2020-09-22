import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { AuthService } from '../services/auth.service';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-offers-history',
  templateUrl: './offers-history.component.html',
  styleUrls: ['./offers-history.component.scss']
})
export class OffersHistoryComponent implements OnInit {

  constructor(private auth:AuthService,private transactionService:TransactionService) { }

  @ViewChild(MatSort) sort: MatSort;
  
  displayedColumns: string[] = ['offerID','action', 'date'];
  
  transactionHistory:HistoryDTO[]

  sortData(sort: MatSort) {
    const data = this.transactionHistory.slice();
    if (!sort.active || sort.direction === '') {
      this.transactionHistory = data;
      return;
    }

    this.transactionHistory = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'date': return a.date>b.date ? isAsc ? 1 :-1  -1 : 1 
        default: return 0;
      }
    });
  }

  ngOnInit(): void { 
    this.auth.authenticationCheck()
    if(this.auth.isAuthenticated)
    this.transactionService.getUserHistory().subscribe((history:HistoryDTO[])=>{this.transactionHistory = history;console.log(history)},()=>null)
  }
}

export interface HistoryDTO{
  date:Date,
  action:String,
  offerId:number
}
