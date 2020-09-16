import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Config {

  constructor() { }

  public API_URL_SERVER = "http://localhost:8085"
  public OFFER_SEARCH_ENDPOINT = "/transactionservice/offers/search"
  public CREATE_OFFER_ENDPOINT = "/transactionservice/transaction/createOffer"
  public REMOVE_OFFER_ENDPOINT = "/transactionservice/transaction/removeOffer"
  public CONCRETE_OFFER_ENDPOINT = "/transactionservice/offers/"
  public REGISTER_USER_ENDPOINT = "/register"
  public LOGIN_USER_ENDPOINT = "/auth"
  public MARKS_WITH_COUNTER_ENDPOINT="/transactionservice/filters/marks"
  public MODELS_WITH_COUNTER_ENDPOINT="/transactionservice/filters/models"
  public LOCATIONS_WITH_COUNTER_ENDPOINT="/transactionservice/filters/locations"
  public OFFER_VISITED_ENDPOINT=""
  public CAR_TYPES_ENDPOINT="/transactionservice/filters/carType"
  public HEADER = { headers:{"Content-type":"application/json"}}
}
