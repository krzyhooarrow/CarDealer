export interface TransactionDTO{
    id?:number
    price?:number
    carType?:String
    model?:String
    mark?:String
    production_year?:number
    fuelType?:String
    location_city?:String
    mileage?:number
    capacity?:number
    power?:number
    gearbox?:String
    vin?:String
    color?:String
    state?:String
    additionalEquipment?:Array<String> 
    username?:String,
    phone?:number,
    email?:String,
    creationDate?:Date;
    userSubscriptions?:Array<number>;
    title?:String
    tags?:String
    description?:String
    images?:Array<String>

}