import { Injectable } from '@angular/core';
import { Config } from './app-config';
import { config, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Offer, OfferRemovalDTO, OfferDTO, SearchDTO } from '../../models/interfaces'
import { AuthService } from './auth.service';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { HistoryDTO } from '../offers-history/offers-history.component';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  

constructor(private config: Config,private http: HttpClient, private authService:AuthService) { }

public getTransactionById(id:Number){ return this.http.get<TransactionDTO>(this.config.API_URL_SERVER.concat(this.config.CONCRETE_OFFER_ENDPOINT).concat(id.toString())) }

public getDistinctCarTypesWithCounter(){  return this.http.get<SelectMap[]>(this.config.API_URL_SERVER.concat(this.config.CAR_TYPES_ENDPOINT)) }

public getDistinctCarMarksWithCounter(){ return this.http.get<SelectMap[]>(this.config.API_URL_SERVER.concat(this.config.MARKS_WITH_COUNTER_ENDPOINT))  }

public getDistinctLocationsWithCounter(){ return this.http.get<SelectMap[]>(this.config.API_URL_SERVER.concat(this.config.LOCATIONS_WITH_COUNTER_ENDPOINT))  }

public getDistinctCarModelsByMarkWithCounter(mark:String){ return this.http.post<SelectMap[]>(this.config.API_URL_SERVER.concat(this.config.MODELS_WITH_COUNTER_ENDPOINT),mark) }

public getAllOffersByQuery(offerDTO:SearchDTO){  return this.http.post<Array<TransactionDTO>>(this.config.API_URL_SERVER.concat(this.config.OFFER_SEARCH_ENDPOINT),offerDTO,this.config.HEADER) }

public removeOffer(id: number){ this.http.post(this.config.API_URL_SERVER.concat(this.config.REMOVE_OFFER_ENDPOINT).concat('/').concat(id.toString()),null,this.authService.getAuthHeader())}

public createOffer(offerDTO: OfferDTO){ this.http.post(this.config.API_URL_SERVER.concat(this.config.CREATE_OFFER_ENDPOINT),offerDTO,this.authService.getAuthHeader())}

public getUserOffers(){  return this.http.get<Array<TransactionDTO>>(this.config.API_URL_SERVER.concat(this.config.GET_USER_OFFERS),this.authService.getAuthHeader()) }

public getUserSubscribedOffers(ids:number[]){  return this.http.post<Array<TransactionDTO>>(this.config.API_URL_SERVER.concat(this.config.GET_USER_SUBSCRIBED_OFFERS),ids,this.authService.getAuthHeader()) }

public getUserHistory(){  return this.http.get<Array<HistoryDTO>>(this.config.API_URL_SERVER.concat(this.config.GET_USER_HISTORY),this.authService.getAuthHeader()) }

}

export interface SelectMap extends Array<string|number>{0:string; 1:number}



