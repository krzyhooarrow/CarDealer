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
  sort;
  constructor(service:TransactionService) { this.service = service; }

   onSearch(){
    this.isLoading = true; 
    this.service.getAllOffersByQuery(
      {
      "type": this.car_type? this.car_type : null,
      "model": this.car_model? this.car_model : null,
      "mark": this.car_mark? this.car_mark : null,
      "production_year_from": this.production_year_from? +this.production_year_from:null,
      "production_year_to": this.production_year_to? +this.production_year_to:null,
      "state":this.state? this.state:null,
      "fuelType":this.fuel_type? this.fuel_type:null,
      "mileage_from":this.mileage_from? +this.mileage_from:null,
      "mileage_to": this.mileage_to? +this.mileage_to:null,
      "capacity_from": this.capacity_from? +this.capacity_from : null,
      "capacity_to": this.capacity_to? +this.capacity_to : null,
      "gearbox" : this.gearbox? this.gearbox : null,
      "power_from" : this.power_from? +this.power_from : null,
      "power_to": this.power_to? +this.power_to : null,
      "lowPrice": this.price_from? +this.price_from : null,
      "highPrice": this.price_to? +this.price_to : null,
      }
  ).subscribe((offers: Array<TransactionDTO>) => 
    {    
         this.transactions = offers
         this.isLoading = false;
     }
  );  }
    
  onSortChange(){
        if(this.transactions)
        if(this.sort == 1)
        this.transactions.sort((t1:TransactionDTO,t2:TransactionDTO) => {return t1.price - t2.price})

        else if(this.sort == 2)
        this.transactions.sort((t1:TransactionDTO,t2:TransactionDTO) => {return t2.price - t1.price})

        else if(this.sort == 3)
        this.transactions.sort((t1:TransactionDTO,t2:TransactionDTO) => {return t2.creationDate > t1.creationDate ? 1:-1;})

        else if(this.sort == 4)
        this.transactions.sort((t1:TransactionDTO,t2:TransactionDTO) => {return t2.creationDate < t1.creationDate ? 1:-1;})
  }
 
  onSelectMark(){   this.service.getDistinctCarModelsByMarkWithCounter(this.car_mark).subscribe((types: Array<SelectMap>) => {this.distinctCarModels = types }) }
  onSelectProductionYearFrom(){  this.production_years_to = this.array_creator(11,1970,5).filter( value => value >= +this.production_year_from);  }
  onSelectMileageFrom(){ this.mileage_to_array = this.array_creator(11,0,25000).filter( value => value >= +this.mileage_from);  } 
  onSelectPriceFrom(){ this.price_to_array = this.array_creator(20,0,20000).filter( value => value >= +this.price_from);  } 
  onSelectCapacityFrom(){ this.capacity_to_array = this.array_creator(20,1,0.2).filter( value => value >= +this.capacity_from); }
  onSelectPowerFrom(){ this.power_to_array = this.array_creator(30,50,25).filter( value => value >= +this.power_from); }

  public car_type:String;
  public car_model:String
  public car_mark:String;
  public production_year_from:String;
  public production_year_to:String;
  public fuel_type:String
  public production_country:String;
  public state:String;
  public price_from:String;
  public price_to:String;
  public mileage_from:String;
  public mileage_to:String;
  public capacity_from:String;
  public capacity_to:String;
  public power_from:String;
  public power_to:String
  public gearbox:String;

  distinctCarTypes:Array<SelectMap>;
  distinctCarMarks:Array<SelectMap>;
  distinctCarModels:Array<SelectMap>;
  distinctFuelTypes:Array<String>;
  distinctProductionCountry:Array<SelectMap>;   

  ngOnInit(): void { 
    this.service.getDistinctCarTypesWithCounter().subscribe((types: Array<SelectMap>) => {this.distinctCarTypes = types })
    this.service.getDistinctCarMarksWithCounter().subscribe((types: Array<SelectMap>) => {this.distinctCarMarks = types })
    this.service.getDistinctFuelTypes().subscribe((types: Array<String>) => {this.distinctFuelTypes = types })
  }


  public production_years_from = this.array_creator(11,1970,5);
  public production_years_to = this.array_creator(11,1970,5);

  public mileage_from_array = this.array_creator(11,0,25000);
  public mileage_to_array = this.array_creator(11,0,25000);

  public price_from_array = this.array_creator(20,0,20000);
  public price_to_array =  this.array_creator(20,0,20000);

  public power_from_array = this.array_creator(30,50,25);
  public power_to_array =  this.array_creator(30,50,25);

  public capacity_from_array = this.array_creator(20,1,0.2);
  public capacity_to_array =  this.array_creator(20,1,0.2);

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

