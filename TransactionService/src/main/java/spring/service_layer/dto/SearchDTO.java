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
    private Integer production_year_from;
    private Integer production_year_to;
    private String state;
    private String fuelType;
    private Integer mileage_from;
    private Integer mileage_to;
    private Integer lowPrice;
    private Integer highPrice;
    private Float capacity_from;
    private Float capacity_to;
    private String gearbox;
    private Integer power_from;
    private Integer power_to;


    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

    public Integer getProduction_year_from() {
        return production_year_from;
    }

    public Integer getProduction_year_to() {
        return production_year_to;
    }

    public String getState() {
        return state;
    }

    public String getFuelType() {
        return fuelType;
    }

    public Integer getMileage_from() {
        return mileage_from;
    }

    public Integer getMileage_to() {
        return mileage_to;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public Integer getHighPrice() {
        return highPrice;
    }

    public Float getCapacity_from() {
        return capacity_from;
    }

    public Float getCapacity_to() {
        return capacity_to;
    }

    public String getGearbox() {
        return gearbox;
    }

    public Integer getPower_from() {
        return power_from;
    }

    public Integer getPower_to() {
        return power_to;
    }
}
