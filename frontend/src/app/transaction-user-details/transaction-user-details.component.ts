import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-transaction-user-details',
  templateUrl: './transaction-user-details.component.html',
  styleUrls: ['./transaction-user-details.component.scss']
})
export class TransactionUserDetailsComponent implements OnInit {

  public isLogged:boolean = true;
  public isSubscribing:boolean = true;
  public price:number;

  public sellerUsername:String;
  public sellerPhoneNumber:String;
  public sellerLocation:String;
  public sellerEmail:String;
  public offerCreationDate:String;

  constructor() { }

  ngOnInit(): void {
  }

}
