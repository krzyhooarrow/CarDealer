import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Config } from './app-config';
import { AuthService } from './auth.service';

@Injectable({
    providedIn: 'root'
  })
  export class SubscriptionService{

constructor(private config: Config,private http: HttpClient, private authService:AuthService) { }

  public getUserSubscriptions(){return this.http.post<number[]>(this.config.API_URL_SERVER.concat(this.config.GET_USER_SUBSCRIPTIONS),null,this.authService.getAuthHeader())  }

  public subscribe(id:number){  return this.http.post<number[]>(this.config.API_URL_SERVER.concat(this.config.SUBSCRIBE_OFFER).concat('/').concat(id.toString()),null,this.authService.getAuthHeader())   }

  public unsubscribe(id:number){return  this.http.post<number[]>(this.config.API_URL_SERVER.concat(this.config.UNSUBSCRIBE_OFFER.concat('/').concat(id.toString())),null,this.authService.getAuthHeader())   }  

  public sendOfferVisitedPost(offerId:number){ this.http.post(this.config.API_URL_SERVER.concat(this.config.POPULARIZE_OFFER_ENDPOINT).concat('/').concat(offerId.toString()),null).subscribe(()=>{},()=>{})  }

  public getOfferPopularity(offerId:number){ return this.http.get(this.config.API_URL_SERVER.concat(this.config.OFFER_WATCHED_ENDPOINT).concat('/').concat(offerId.toString()))  }

}