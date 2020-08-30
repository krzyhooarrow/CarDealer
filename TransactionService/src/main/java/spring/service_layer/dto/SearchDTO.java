package spring.service_layer.dto;

import spring.repository_layer.models.cars.Car;
import spring.repository_layer.models.cars.CarModel;
import spring.repository_layer.models.cars.CarType;
import spring.repository_layer.models.cars.FuelType;

import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;
import java.util.LinkedList;
import java.util.List;

public class SearchDTO {


    private String type;
    private String model;
    private String mark;
    private Integer production_year;
    private String fuelType;
    private String country;
    private String location_country;
    private String location_city;
    private Integer lowPrice;
    private Integer highPrice;


    public String getType() {
        return type;
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

    public String getLocation_country() {
        return location_country;
    }

    public String getLocation_city() {
        return location_city;
    }

    public Integer getLowPrice() {
        return lowPrice == null ?  0 : lowPrice;
    }

    public Integer getHighPrice() {
        return highPrice == null ? Integer.MAX_VALUE : highPrice;
    }

    public String getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", mark='" + mark + '\'' +
                ", production_year=" + production_year +
                ", fuelType='" + fuelType + '\'' +
                ", country='" + country + '\'' +
                ", location_country='" + location_country + '\'' +
                ", location_city='" + location_city + '\'' +
                ", lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                '}';
    }
}
