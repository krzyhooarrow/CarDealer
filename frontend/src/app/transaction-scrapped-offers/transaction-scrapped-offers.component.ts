import { Component, Input, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transaction-scrapped-offers',
  templateUrl: './transaction-scrapped-offers.component.html',
  styleUrls: ['./transaction-scrapped-offers.component.scss']
})
export class TransactionScrappedOffersComponent implements OnInit {

  @Input() transaction:TransactionDTO;

  parameters:Map<String,String>;
  displayedColumns: string[] = ['key','value'];

  constructor(public transactionService:TransactionService) { }

  ngOnInit(): void { this.getScrappedOffers(this.transaction.id)}


  getScrappedOffers(offerId:number){
    this.transactionService.getVINData(offerId).subscribe(
      (values) => { if(values && Object.entries(values.parameters).length !== 0) this.parameters = values.parameters},
      ()=>{},()=>{}      
    );
  }
}
