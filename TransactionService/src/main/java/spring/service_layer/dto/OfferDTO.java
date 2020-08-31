package spring.service_layer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.Car;
import spring.repository_layer.models.cars.CarModel;
import spring.repository_layer.models.cars.CarType;
import spring.repository_layer.models.cars.FuelTypeEnum;


import java.util.List;
import java.util.function.Function;

@NoArgsConstructor
public class OfferDTO {

    private int id;
    private Integer price;
    private String description;
    private List<String> image;
    private String carType;
    private String model;
    private Integer production_year;
    private String fuelType;
    private String country;
    private List<String> additionalEquipment;
    private String location_country;
    private String location_city;
    private String username;

    public int getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImage() {
        return image;
    }

    public String getCarType() {
        return carType;
    }

    public String getModel() {
        return model;
    }

    public Integer getProduction_year() {
        return production_year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getCountry() {
        return country;
    }

    public List<String> getAdditionalEquipment() {
        return additionalEquipment;
    }

    public String getLocation_country() {
        return location_country;
    }

    public String getLocation_city() {
        return location_city;
    }

    public String getUsername() {
        return username;
    }
}
