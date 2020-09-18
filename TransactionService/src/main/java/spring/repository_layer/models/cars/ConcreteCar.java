package spring.repository_layer.models.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import spring.repository_layer.models.Offer;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@NonNull
//@JsonSerialize(using = ConcreteCarSerializer.class)
public class ConcreteCar{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
//    @JsonIgnore
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne
    private FuelType fuelType;
    private String country;

    @ElementCollection
    private List<String> additionalEquipment;
    private String location_country;
    private String location_city;

    private int mileage;
    private float capacity;
    private int power;
    @Enumerated(EnumType.STRING)
    private GearBox gearbox;
    private String vin;
    private String color;
    @Enumerated(EnumType.STRING)
    private State state;

    public ConcreteCar(Car car, FuelType fuelType, String country, List<String> additionalEquipment, String location_country, String location_city) {
        this.car = car;
        this.fuelType = fuelType;
        this.country = country;
        this.additionalEquipment = additionalEquipment;
        this.location_country = location_country;
        this.location_city = location_city;
    }

    public Car getCar() {
        return car;
    }

    public FuelType getFuelType() {
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

    public int getMileage() {
        return mileage;
    }

    public float getCapacity() {
        return capacity;
    }

    public int getPower() {
        return power;
    }

    public GearBox getGearbox() {
        return gearbox;
    }

    public String getVin() {
        return vin;
    }

    public String getColor() {
        return color;
    }

    public State getState() {
        return state;
    }

}
