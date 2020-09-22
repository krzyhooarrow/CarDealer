import { Injectable } from '@angular/core';
import { Config } from './app-config';
import { config, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Offer, OfferRemovalDTO, OfferDTO, SearchDTO } from '../../models/interfaces'
import { AuthService } from './auth.service';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { HistoryDTO } from '../offers-history/offers-history.component';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  

constructor(private config: Config,private http: HttpClient, private authService:AuthService, private cookieService:CookieService) { }

public getTransactionById(id:Number){ return this.http.get<TransactionDTO>(this.config.API_URL_SERVER.concat(this.config.CONCRETE_OFFER_ENDPOINT).concat(id.toString())) }

public getDistinctCarTypesWithCounter(){  return this.http.get<SelectMap[]>(this.config.API_URL_SERVER.concat(this.config.CAR_TYPES_ENDPOINT)) }

public getDistinctCarMarksWithCounter(){ return this.http.get<SelectMap[]>(this.config.API_URL_SERVER.concat(this.config.MARKS_WITH_COUNTER_ENDPOINT))  }

public getDistinctCarModelsByMarkWithCounter(mark:String){ return this.http.post<SelectMap[]>(this.config.API_URL_SERVER.concat(this.config.MODELS_WITH_COUNTER_ENDPOINT),mark) }

public getDistinctFuelTypes(){return  this.http.get<String[]>(this.config.API_URL_SERVER.concat(this.config.GET_FUEL_TYPES)) }

public getDistinctAdditionalEquipment(){return  this.http.get<String[]>(this.config.API_URL_SERVER.concat(this.config.GET_ADDITIONAL_EQUIPMENT)) }

public getAllOffersByQuery(offerDTO:SearchDTO){  return this.http.post<Array<TransactionDTO>>(this.config.API_URL_SERVER.concat(this.config.OFFER_SEARCH_ENDPOINT),offerDTO,this.config.HEADER) }

public removeOffer(id: number){ return this.http.get(this.config.API_URL_SERVER.concat(this.config.REMOVE_OFFER_ENDPOINT).concat('/').concat(id.toString()),this.authService.getAuthHeader())}

public createOffer(offerDTO){return this.http.post(this.config.API_URL_SERVER.concat(this.config.CREATE_OFFER_ENDPOINT),offerDTO,this.authService.getAuthHeader())}

public getUserOffers(){  return this.http.get<Array<TransactionDTO>>(this.config.API_URL_SERVER.concat(this.config.GET_USER_OFFERS),this.authService.getAuthHeader()) }

public getUserSubscribedOffers(ids:number[]){  return this.http.post<Array<TransactionDTO>>(this.config.API_URL_SERVER.concat(this.config.GET_USER_SUBSCRIBED_OFFERS),ids,this.authService.getAuthHeader()) }

public getUserHistory(){  return this.http.get<Array<HistoryDTO>>(this.config.API_URL_SERVER.concat(this.config.GET_USER_HISTORY),this.authService.getAuthHeader()) }

public uploadFileToOffer(offerId,image){ return this.http.post(this.config.API_URL_SERVER.concat(this.config.UPLOAD_IMAGE).concat('/').concat(offerId),image,{ headers:{"Authorization":"Bearer ".concat(this.cookieService.get("JWT"))}})}}

export interface SelectMap extends Array<string|number>{0:string; 1:number}



