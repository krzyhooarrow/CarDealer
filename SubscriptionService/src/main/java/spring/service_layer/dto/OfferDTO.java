package spring.service_layer.dto;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class OfferDTO {

    private Long id;
    private String title;
    private String tags;
    private Integer price;
    private String description;
    private String carType;
    private String mark;
    private String model;
    private Integer production_year;
    private String fuelType;
    private String location;
    private Integer mileage;
    private Float capacity;
    private Integer power;
    private String gearbox;
    private String vin;
    private String state;
    private List<String> additionalEquipment;

    public Long getId() { return id;  }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCarType() {
        return carType;
    }

    public String getMark() {
        return mark;
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

    public String getLocation() {
        return location;
    }

    public Integer getMileage() {
        return mileage;
    }

    public Float getCapacity() {
        return capacity;
    }

    public Integer getPower() {
        return power;
    }

    public String getGearbox() {
        return gearbox;
    }

    public String getVin() {
        return vin;
    }

    public String getState() {
        return state;
    }

    public List<String> getAdditionalEquipment() {
        return additionalEquipment;
    }

    @Override
    public String toString() {
        return "OfferDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", carType='" + carType + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", production_year=" + production_year +
                ", fuelType='" + fuelType + '\'' +
                ", location='" + location + '\'' +
                ", mileage=" + mileage +
                ", capacity=" + capacity +
                ", power=" + power +
                ", gearbox='" + gearbox + '\'' +
                ", vin='" + vin + '\'' +
                ", state='" + state + '\'' +
                ", additionalEquipment=" + additionalEquipment +
                '}';
    }
}
