import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Config {

  constructor() { }

  public API_URL_SERVER = "http://localhost:8080"
  public OFFER_SEARCH_ENDPOINT = "/transaction/search"
  public CREATE_OFFER_ENDPOINT = "/transaction/createOffer"
  public REMOVE_OFFER_ENDPOINT = "/transaction/removeOffer"
  public REGISTER_USER_ENDPOINT = "/register"
  public LOGIN_USER_ENDPOINT = "/auth"
  public MARKS_WITH_COUNTER_ENDPOINT=""
  public MODELS_WITH_COUNTER_ENDPOINT=""
  public OFFER_VISITED_ENDPOINT=""
  public CAR_TYPES_ENDPOINT=""
  public HEADER = { headers:{"Content-type":"application/json"}}
}
