import { Injectable } from '@angular/core';
import { Config } from './app-config';
import { config, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Offer, OfferRemovalDTO, OfferDTO, SearchDTO } from '../../models/interfaces'
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private config: Config,private http: HttpClient, private authService:AuthService) { }


  public getDistinctCarTypesWithCounter(){
    let typesCounter:Map<String,number>
    this.http.get<Map<String,number>>(this.config.API_URL_SERVER.concat(this.config.CAR_TYPES_ENDPOINT)).subscribe((types:Map<String,number>) => typesCounter = types);
    return typesCounter;
  }

  public getDistinctCarMarksWithCounter(){
    let marksCounter:Map<String,number>
    this.http.get<Map<String,number>>(this.config.API_URL_SERVER.concat(this.config.MARKS_WITH_COUNTER_ENDPOINT)).subscribe( (map:Map<String,number>) => marksCounter = map); 
    return marksCounter;
  }

  public getDistinctCarModelsByMarkWithCounter(){
    let modelsCounter:Map<String,number>
    this.http.get<Map<String,number>>(this.config.API_URL_SERVER.concat(this.config.MODELS_WITH_COUNTER_ENDPOINT)).subscribe( (map:Map<String,number>) => modelsCounter = map);    
    return modelsCounter;
    
  }

  public getAllOffersByQuery(offerDTO:SearchDTO){ 
   let offersList:Array<Offer>;
   this.http.post<Array<Offer>>(this.config.API_URL_SERVER.concat(this.config.OFFER_SEARCH_ENDPOINT),offerDTO,this.config.HEADER).subscribe((offers: Array<Offer>) => offersList = { ...offers });
   return offersList;
  }


public removeOffer(offerDTO: OfferRemovalDTO){ this.http.post(this.config.API_URL_SERVER.concat(this.config.REMOVE_OFFER_ENDPOINT),offerDTO,this.authService.getAuthHeader()).subscribe()}

public createOffer(offerDTO: OfferDTO){ this.http.post(this.config.API_URL_SERVER.concat(this.config.CREATE_OFFER_ENDPOINT),offerDTO,this.authService.getAuthHeader()).subscribe()}

public sendOfferVisitedPost(offerId:number){ this.http.post(this.config.API_URL_SERVER.concat(this.config.OFFER_VISITED_ENDPOINT),offerId).subscribe()  }

}
