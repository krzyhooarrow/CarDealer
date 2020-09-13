export interface TransactionDTO{
    price:number
    carDTO:TransactionCarDTO
    userDTO:TransactionUserDTO
    detailsDTO:TransactionDetailsDTO
    desc:TransactionDescriptionDTO
    images:TransactionImagesDTO
}


export interface TransactionCarDTO{

    carType:String
    model:String
    mark:String
    production_year:number
    fuelType:String
    location_city:String
    mileage:number
    capacity:number
    power:number
    gearbox:String
    VIN:number
    color:String
    state:String
    equipment:TransactionEquipmentDTO
}

export interface TransactionUserDTO{
    username:String,
    phone:number,
    location:String,
    email:String,
    creationDate:Date;
    userSubscriptions:Array<number>;
}

export interface TransactionDetailsDTO{
    title:String
    tags:Array<String>
}

export interface TransactionEquipmentDTO{possessedEquipment:Array<String> }

export interface TransactionDescriptionDTO{  description:String }

export interface TransactionImagesDTO{ images:Array<String>}