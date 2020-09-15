import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Config {

  constructor() { }

  public API_URL_SERVER = "http://localhost:8081"
  public OFFER_SEARCH_ENDPOINT = "/transaction/search"
  public CREATE_OFFER_ENDPOINT = "/transaction/createOffer"
  public REMOVE_OFFER_ENDPOINT = "/transaction/removeOffer"
  public CONCRETE_OFFER_ENDPOINT = "/transaction/"
  public REGISTER_USER_ENDPOINT = "/register"
  public LOGIN_USER_ENDPOINT = "/auth"
  public MARKS_WITH_COUNTER_ENDPOINT="/filters/marks"
  public MODELS_WITH_COUNTER_ENDPOINT="/filters/models"
  public LOCATIONS_WITH_COUNTER_ENDPOINT="/filters/locations"
  public OFFER_VISITED_ENDPOINT=""
  public CAR_TYPES_ENDPOINT="/filters/carType"
  public HEADER = { headers:{"Content-type":"application/json"}}
}
