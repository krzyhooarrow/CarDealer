package spring.service_layer.dto;

import spring.repository_layer.models.Offer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDTO {


    private Long id;
    private Integer price;
    private String carType;
    private String model;
    private String mark;
    private Integer production_year;
    private String fuelType;
    private String location_city;
    private int mileage = 1000;
    private float capacity = 1.978F;
    private int power = 100;
    private String gearbox = "manual";
    private String VIN = "12312312321";
    private String color = "red";
    private String state = "damaged";
    private List<String> additionalEquipment = new ArrayList<>();

    private String username = "test transactrion";
    private int phone = 123123123;
    private String email = "test transactrion";
    private Date creationDate = new Date();

    private String title = "title of offer";
    private String tags = " tags of offer";
    private String description = "test transactrion";
    private List<String> images = new ArrayList<>();

    public TransactionDTO(Offer offer){
    this.id = offer.getId();
    this.price = offer.getPrice();
    this.carType = offer.getCar().getCar().getType().getCarType();
    this.model = offer.getCar().getCar().getModel().getModel();
    this.mark = offer.getCar().getCar().getModel().getCarMark().getMark();
    this.production_year = offer.getCar().getCar().getProduction_year();
    this.fuelType = offer.getCar().getFuelType().getFuelTypeEnum().name();
    this.location_city = offer.getCar().getLocation_city();
//    this.additionalEquipment = offer.getCar().getAdditionalEquipment();
//    this.username = offer.getUser().getUsername();
//    this.email = offer.getUser().getEmail();
//    this.creationDate = offer.getOfferCreationTime();
//    this.description = offer.getDescription();
//    this.images = offer.getImage();
    }

    public Integer getPrice() {
        return price;
    }

    public String getCarType() {
        return carType;
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

    public Integer getProduction_year() {
        return production_year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getLocation_city() {
        return location_city;
    }

    public int getMileage() {
        return mileage;
    }

    public float getCapacity() {
        return capacity;
    }

    public int getPower() {
        return power;
    }

    public String getGearbox() {
        return gearbox;
    }

    public String getVIN() {
        return VIN;
    }

    public String getColor() {
        return color;
    }

    public String getState() {
        return state;
    }

    public List<String> getAdditionalEquipment() {
        return additionalEquipment;
    }

    public String getUsername() {
        return username;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

    public Long getId() {
        return id;
    }
}
