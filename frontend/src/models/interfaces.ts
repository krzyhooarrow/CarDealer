
export interface Offer{
    id:number
    car:ConcreteCar
    price:number
    description:String
    image:Array<String>
    user:String
    offerCreationTime:Date
}

export interface ConcreteCar{
    id:number
    car:Car
    fuelType:FuelType
    country:String
    additionalEquipment:Array<String>
    location_country:String
    location_city:String
}

export interface FuelType{
    id:number
    fuelTypeEnum:String
}

export interface Car{ 
    id:number
    type:CarType
    model:CarModel
    production_year:number    
}

export interface CarType{
    id:number
    carType:String
}
export interface CarModel{
    id:number
    model:String
    carMark:CarMark
}

export interface CarMark{
    id:number
    mark:String
}

export interface SearchDTO{
    type:String
    model:String
    mark:String
    production_year:number
    fuelType:String
    country:String
    additionalEquipment?:Array<String>
    location_country:String
    location_city:String
    highPrice:number
    lowPrice:number
}

export interface OfferDTO{
    price:number
    user:UserOfferDTO
    description:String
    image:Array<String>
    
    
    
    carType:String
    model:String
    production_year:number
    fuelType:String
    country:String
    additionalEquipment:Array<String>
    location_country:String
    location_city:String
    username:String
}

export interface OfferRemovalDTO{
    offerId: number
    username: String
}

export interface UserDTO{
    username:String,
    password:String,
    email:String
}


export interface UserOfferDTO{
    username:String,
    phone:number,
    location:String,
    email:String,
    creationDate:Date;
    userSubscriptions:Array<number>;
}
