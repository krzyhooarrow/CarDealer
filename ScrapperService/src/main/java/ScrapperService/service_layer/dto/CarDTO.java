package ScrapperService.service_layer.dto;

import ScrapperService.repository_layer.models.Transmission;

public class CarDTO {

    private Long id;
    private int power;
    private int mileage;
    private int price;
    private float capacity;
    private String make;
    private String model;
    private int productionYear;

    public int getPower() {
        return power;
    }

    public int getMileage() {
        return mileage;
    }

    public int getPrice() {
        return price;
    }

    public float getCapacity() {
        return capacity;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public Long getId() {
        return id;
    }
}
