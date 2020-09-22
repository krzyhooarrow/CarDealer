import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { SelectMap, TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-new-offer',
  templateUrl: './new-offer.component.html',
  styleUrls: ['./new-offer.component.scss']
})
export class NewOfferComponent implements OnInit {

  createdTransaction:TransactionDTO;

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  distinctCarTypes:Array<SelectMap>;
  distinctCarMarks:Array<SelectMap>;
  distinctCarModels:Array<SelectMap>;
  distinctFuelTypes:Array<String>;
  distinctProductionCountry:Array<SelectMap>; 
  distinctAdditionalEquipmentList:Array<String>;

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
      title: ['', [Validators.required,Validators.minLength(5)]],  tags: ['', Validators.required],  
      price: ['', Validators.required],  description : ['',],
    });

    this.secondFormGroup = this._formBuilder.group({

      type: ['', Validators.required],
      mark: ['', Validators.required],
      model: ['', Validators.required],
      year: ['', Validators.required],
      fuel: ['', Validators.required],
      location: ['', [Validators.required, Validators.maxLength(15)]],
      mileage: ['', [Validators.required,Validators.max(1000000)]],
      capacity: ['', [Validators.required,Validators.max(7000)]],
      power: ['', [Validators.required,Validators.max(1000)]],
      gearbox: ['', Validators.required],
      vin: ['', [Validators.required,Validators.minLength(11),Validators.maxLength(17)]],
      state: ['undamaged', Validators.required],
      equipment: ['', ]
    });

    this.service.getDistinctCarTypesWithCounter().subscribe((types: Array<SelectMap>) => {this.distinctCarTypes = types })
    this.service.getDistinctCarMarksWithCounter().subscribe((types: Array<SelectMap>) => {this.distinctCarMarks = types })
    this.service.getDistinctFuelTypes().subscribe((types: Array<String>) => {this.distinctFuelTypes = types })
    this.service.getDistinctAdditionalEquipment().subscribe((types: Array<String>) => {this.distinctAdditionalEquipmentList = types })
  }

  onSelectMark(value:String){   this.service.getDistinctCarModelsByMarkWithCounter(value).subscribe((types: Array<SelectMap>) => {this.distinctCarModels = types }) }

  array_creator(howManyNumbers: number, startFrom: number, iterator:number): number[] { return [...Array(howManyNumbers).keys()].map(i => i*iterator + startFrom);  }

  files: File[] = [];
 
  onSelect(event) { this.files.push(...event.addedFiles);}
 
  onRemove(event) { this.files.splice(this.files.indexOf(event), 1);}

  createTransaction(){

    let offerDTO:OfferDTO = {

      title:this.firstFormGroup.get('title').value,
      tags:this.firstFormGroup.get('tags').value,
      description:this.firstFormGroup.get('description').value,
      price:+this.firstFormGroup.get('price').value,
      carType:this.secondFormGroup.get('type').value,
      mark:this.secondFormGroup.get('mark').value,
      model:this.secondFormGroup.get('model').value,
      production_year:this.secondFormGroup.get('year').value,
      fuelType:this.secondFormGroup.get('fuel').value,
      location_city:this.secondFormGroup.get('location').value,
      mileage:this.secondFormGroup.get('mileage').value,
      capacity:this.secondFormGroup.get('capacity').value,
      power:this.secondFormGroup.get('power').value,
      gearbox:this.secondFormGroup.get('gearbox').value,
      vin:this.secondFormGroup.get('vin').value,
      state:this.secondFormGroup.get('state').value,
      additionalEquipment:this.secondFormGroup.get('equipment')?.value ? this.secondFormGroup.get('equipment').value  : [],
    
    }


    this.service.createOffer(offerDTO).subscribe((id:number)=>{

      for(let file of this.files){
      let form = new FormData();
      form.append('file',file)
      this.service.uploadFileToOffer(id,form).subscribe(
        (output:string)=>console.log(output), (output:string)=>console.log(output)
      )
      }
    }, (output:string)=>console.log(output));
  }

}
export interface OfferDTO{
  title:string
  tags:string
  description:string
  price:number
  carType:string
  mark:string
  model:string
  production_year:number
  fuelType:string
  location_city:string
  mileage:string
  capacity:string
  power:number
  gearbox:string
  vin:string
  state:string
  additionalEquipment:string[]
}
