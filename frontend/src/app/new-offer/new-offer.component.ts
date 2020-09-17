import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SelectMap, TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-new-offer',
  templateUrl: './new-offer.component.html',
  styleUrls: ['./new-offer.component.scss']
})
export class NewOfferComponent implements OnInit {

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  distinctCarTypes:Array<SelectMap>;
  distinctCarMarks:Array<SelectMap>;
  distinctCarModels:Array<SelectMap>;
  distinctLocations:Array<SelectMap>;
  distinctFuelTypes:Array<SelectMap>;
  distinctProductionCountry:Array<SelectMap>; 
  distinctAdditionalEquipmentList:Array<String> = ["123","456"];

  public car_type:String;
  public car_model:String
  public car_mark:String;
  public production:String;
  public fuel_type:String
  public production_country:String;
  public state:String;
  public location:String;
  public price:String;
  public mileage:String;
  public production_years = this.array_creator(50,1970,1);

  private service:TransactionService;
  
  constructor(private _formBuilder: FormBuilder,service:TransactionService) { this.service = service;}

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({  
      title: ['', Validators.required],  tags: ['', Validators.required],  
      price: ['', Validators.required],  description : ['',],
    });

    this.secondFormGroup = this._formBuilder.group({

      type: ['', Validators.required],
      mark: ['', Validators.required],
      model: ['', Validators.required],
      year: ['', Validators.required],
      fuel: ['', Validators.required],
      location: ['', Validators.required],
      mileage: ['', Validators.required],
      capacity: ['', Validators.required],
      power: ['', Validators.required],
      gearbox: ['', Validators.required],
      vin: ['', Validators.required],
      state: ['undamaged', Validators.required],
      equipment: ['', ]
    });

    this.service.getDistinctCarTypesWithCounter().subscribe((types: Array<SelectMap>) => {this.distinctCarTypes = types })
    this.service.getDistinctCarMarksWithCounter().subscribe((types: Array<SelectMap>) => {this.distinctCarMarks = types })
    this.service.getDistinctLocationsWithCounter().subscribe((types: Array<SelectMap>) => {this.distinctLocations = types })  
  }

  onSelectMark(value:String){   this.service.getDistinctCarModelsByMarkWithCounter(value).subscribe((types: Array<SelectMap>) => {this.distinctCarModels = types }) }

  array_creator(howManyNumbers: number, startFrom: number, iterator:number): number[] { return [...Array(howManyNumbers).keys()].map(i => i*iterator + startFrom);  }

  files: File[] = [];
 
  onSelect(event) { this.files.push(...event.addedFiles);}
 
  onRemove(event) { this.files.splice(this.files.indexOf(event), 1);}


  onNext(){console.log(this.firstFormGroup);console.log(this.secondFormGroup)}

}
