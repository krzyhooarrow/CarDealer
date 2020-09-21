package spring.service_layer.dto;

import spring.repository_layer.models.Offer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionDTO {


    private Long id;
    private Integer price;
    private String carType;
    private String model;
    private String mark;
    private Integer production_year;
    private String fuelType;
    private String location_city;
    private final Integer mileage ;
    private final Float capacity ;
    private final Integer power ;
    private String gearbox ;
    private String VIN;
    private String color;
    private String state ;
    private List<String> additionalEquipment;

    private String username ;
    private int phone ;
    private String email;
    private Date creationDate = new Date();

    private String title;
    private String tags;
    private String description;
    private List<String> images;

    public TransactionDTO(Offer offer){
        if(offer.getId()!=null) this.id = offer.getId();
        if(offer.getPrice()!=null) this.price = offer.getPrice();
        if(offer.getCar().getCar().getType().getCarType()!=null) this.carType = offer.getCar().getCar().getType().getCarType();
        if(offer.getCar().getCar().getModel().getModel()!=null) this.model = offer.getCar().getCar().getModel().getModel();
        if(offer.getCar().getCar().getModel().getCarMark().getMark()!=null) this.mark = offer.getCar().getCar().getModel().getCarMark().getMark();
        if(offer.getCar().getCar().getProduction_year()!=null) this.production_year = offer.getCar().getCar().getProduction_year();
        if(offer.getCar().getFuelType()!=null) this.fuelType = offer.getCar().getFuelType().name();
        if(offer.getCar().getLocation_city()!=null) this.location_city = offer.getCar().getLocation_city();
        if(offer.getCar().getAdditionalEquipment()!=null) this.additionalEquipment = offer.getCar()
                .getAdditionalEquipment().stream().map(String::valueOf).collect(Collectors.toList());
        if(offer.getUser().getUsername()!=null) this.username = offer.getUser().getUsername();
        if(offer.getUser().getEmail()!=null) this.email = offer.getUser().getEmail();
        if(offer.getOfferCreationTime()!=null) this.creationDate = offer.getOfferCreationTime();
        if(offer.getDescription()!=null) this.description = offer.getDescription();
        if(offer.getImage()!=null) this.images = offer.getImage();
        if(offer.getTitle()!=null)this.title = offer.getTitle();
        if(offer.getTags()!=null)this.tags = offer.getTags();
        if(offer.getCar().getState()!=null)this.state = offer.getCar().getState().name();
        if(offer.getCar().getColor()!=null)this.color = offer.getCar().getColor();
        if(offer.getCar().getVin()!=null)this.VIN = offer.getCar().getVin();
        if(offer.getCar().getGearbox()!=null)this.gearbox = offer.getCar().getGearbox().name();
        this.mileage = offer.getCar().getMileage();
        this.capacity = offer.getCar().getCapacity();
        this.power = offer.getCar().getPower();
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
