import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { TransactionService } from '../services/transaction.service';
import { SelectMap } from '../services/transaction.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  private service:TransactionService;
  transactions:Array<TransactionDTO>;
  isLoading:boolean;
  pageSize = 10;
  currentPage = 1;

  constructor(service:TransactionService) { this.service = service; }

   onSearch(){
    this.isLoading = true; 
    this.service.getAllOffersByQuery(
      {
      "type": "Sedan",
      "model": null,
      "mark": null,
      "production_year": 2010,
      "fuelType": "diesel",
      "country": null,
      "additionalEquipment": null,
      "location_country": null,
      "location_city": null,
      "highPrice":12000,
      "lowPrice": -1000
  }
  ).subscribe((offers: Array<TransactionDTO>) => 
    {    this.transactions = offers
         this.isLoading = false;
     }
  );  }

 
  onSelectMark(){   this.service.getDistinctCarModelsByMarkWithCounter(this.car_mark).subscribe((types: Array<SelectMap>) => {this.distinctCarModels = types }) }
  onSelectProductionYearFrom(){  this.production_years_to = this.array_creator(11,1970,5).filter( value => value >= +this.production_year_from);  }
  onSelectMileageFrom(){ this.mileage_to_array = this.array_creator(11,0,25000).filter( value => value >= +this.mileage_from);  } 
  onSelectPriceFrom(){ this.price_to_array = this.array_creator(20,0,20000).filter( value => value >= +this.price_from);  } 

  public car_type:String;
  public car_model:String
  public car_mark:String;
  public production_year_from:String;
  public production_year_to:String;
  public fuel_type:String
  public production_country:String;
  public state:String;
  public location_city:String;
  public location_country:String;
  public price_from:String;
  public price_to:String;
  public mileage_from:String;
  public mileage_to:String;

  distinctCarTypes:Array<SelectMap>;
  distinctCarMarks:Array<SelectMap>;
  distinctCarModels:Array<SelectMap>;
  distinctLocations:Array<SelectMap>;
  distinctFuelTypes:Array<SelectMap>;
  distinctProductionCountry:Array<SelectMap>;   

  ngOnInit(): void { 
    this.service.getDistinctCarTypesWithCounter().subscribe((types: Array<SelectMap>) => {this.distinctCarTypes = types })
    this.service.getDistinctCarMarksWithCounter().subscribe((types: Array<SelectMap>) => {this.distinctCarMarks = types })
    this.service.getDistinctLocationsWithCounter().subscribe((types: Array<SelectMap>) => {this.distinctLocations = types })
  }


  public production_years_from = this.array_creator(11,1970,5);
  public production_years_to = this.array_creator(11,1970,5);

  public mileage_from_array = this.array_creator(11,0,25000);
  public mileage_to_array = this.array_creator(11,0,25000);

  public price_from_array = this.array_creator(20,0,20000);
  public price_to_array =  this.array_creator(20,0,20000);

  array_creator(howManyNumbers: number, startFrom: number, iterator:number): number[] {
    return [...Array(howManyNumbers).keys()].map(i => i*iterator + startFrom);
  }

  pageEvent: PageEvent;

  transactions_selector(transactionList:TransactionDTO[]){
    if (this.pageEvent)
    return transactionList.slice(this.pageEvent.pageIndex*this.pageEvent.pageSize,(this.pageEvent.pageIndex+1)*this.pageEvent.pageSize)  
    return transactionList.slice(0,this.pageSize) 
   }
}

