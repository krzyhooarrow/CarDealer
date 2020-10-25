package spring.service_layer.dto;

import lombok.NoArgsConstructor;
import spring.repository_layer.models.Offer;


import java.util.List;
import java.util.stream.Collectors;

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

    public OfferDTO(Offer offer) {
        if (offer.getId() != null) this.id = offer.getId();
        if (offer.getPrice() != null) this.price = offer.getPrice();
        if (offer.getCar().getCar().getType().getCarType() != null)
            this.carType = offer.getCar().getCar().getType().getCarType();
        if (offer.getCar().getCar().getModel().getModel() != null)
            this.model = offer.getCar().getCar().getModel().getModel();
        if (offer.getCar().getCar().getModel().getCarMark().getMark() != null)
            this.mark = offer.getCar().getCar().getModel().getCarMark().getMark();
        if (offer.getCar().getCar().getProduction_year() != null)
            this.production_year = offer.getCar().getCar().getProduction_year();
        if (offer.getCar().getFuelType() != null) this.fuelType = offer.getCar().getFuelType().name();
        if (offer.getCar().getAdditionalEquipment() != null) this.additionalEquipment = offer.getCar()
                .getAdditionalEquipment().stream().map(String::valueOf).collect(Collectors.toList());
        if (offer.getDescription() != null) this.description = offer.getDescription();
        if (offer.getTitle() != null) this.title = offer.getTitle();
        if (offer.getTags() != null) this.tags = offer.getTags();
        if (offer.getCar().getState() != null) this.state = offer.getCar().getState().name();
        if (offer.getCar().getVin() != null) this.vin = offer.getCar().getVin();
        if (offer.getCar().getGearbox() != null) this.gearbox = offer.getCar().getGearbox().name();
        this.mileage = offer.getCar().getMileage();
        this.capacity = offer.getCar().getCapacity();
        this.power = offer.getCar().getPower();
    }

    public OfferDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

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
