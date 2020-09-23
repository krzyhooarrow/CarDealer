import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Config {

  constructor() { }

  // AUTH 
  public API_URL_SERVER = "http://localhost:8085"

  public REGISTER_USER_ENDPOINT = "/register"
  public LOGIN_USER_ENDPOINT = "/auth"

  private SUBS_SERVICE_NAME="subscriptionservice"
  private TRANSACTION_SERVICE_NAME="balancer/transactionservice"

  // TRANSACTION SERVICE ENDPOINTS
  public OFFER_SEARCH_ENDPOINT = "/"+this.TRANSACTION_SERVICE_NAME+"/offers/search"
  public CREATE_OFFER_ENDPOINT = "/"+this.TRANSACTION_SERVICE_NAME+"/transaction/createOffer"
  public UPLOAD_IMAGE = "/"+this.TRANSACTION_SERVICE_NAME+"/transaction/uploadImages"
  public REMOVE_OFFER_ENDPOINT = "/"+this.TRANSACTION_SERVICE_NAME+"/transaction/removeOffer"
  public CONCRETE_OFFER_ENDPOINT = "/"+this.TRANSACTION_SERVICE_NAME+"/offers/"
  public MARKS_WITH_COUNTER_ENDPOINT="/"+this.TRANSACTION_SERVICE_NAME+"/filters/marks"
  public MODELS_WITH_COUNTER_ENDPOINT="/"+this.TRANSACTION_SERVICE_NAME+"/filters/models"
  public GET_FUEL_TYPES="/"+this.TRANSACTION_SERVICE_NAME+"/filters/fuelTypes"
  public GET_ADDITIONAL_EQUIPMENT="/"+this.TRANSACTION_SERVICE_NAME+"/filters/additionalEquipment"
  public LOCATIONS_WITH_COUNTER_ENDPOINT="/"+this.TRANSACTION_SERVICE_NAME+"/filters/locations"
  public CAR_TYPES_ENDPOINT="/"+this.TRANSACTION_SERVICE_NAME+"/filters/carType"
  public GET_USER_SUBSCRIBED_OFFERS="/"+this.TRANSACTION_SERVICE_NAME+"/user/getSubscriptions"
  public GET_USER_OFFERS="/"+this.TRANSACTION_SERVICE_NAME+"/user/getOffers"
  public GET_USER_HISTORY="/"+this.TRANSACTION_SERVICE_NAME+"/user/getHistory"

  // SUBSCRIPTIONS SERVICE ENDPOINTS
  public POPULARIZE_OFFER_ENDPOINT="/"+this.SUBS_SERVICE_NAME+"/popularity/popularize"
  public OFFER_WATCHED_ENDPOINT="/"+this.SUBS_SERVICE_NAME+"/popularity/getPopularity"
  public GET_USER_SUBSCRIPTIONS="/"+this.SUBS_SERVICE_NAME+"/subscriptions/getSubscriptions"
  public SUBSCRIBE_OFFER="/"+this.SUBS_SERVICE_NAME+"/subscriptions/subscribe"
  public UNSUBSCRIBE_OFFER="/"+this.SUBS_SERVICE_NAME+"/subscriptions/unsubscribe"

  public HEADER = { headers:{"Content-type":"application/json"}}
}
