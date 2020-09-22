package spring.repository_layer.models.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@NonNull
public class ConcreteCar{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private Transmission gearbox;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Equipment> additionalEquipment;
    private int mileage;
    private float capacity;
    private int power;
    private String vin;
    private String color;

    public ConcreteCar(Car car, State state, Transmission gearbox, FuelType fuelType, List<Equipment> additionalEquipment,
                       int mileage, float capacity, int power, String vin, String color) {

        this.car = car;
        this.state = state;
        this.gearbox = gearbox;
        this.fuelType = fuelType;
        this.additionalEquipment = additionalEquipment;
        this.mileage = mileage;
        this.capacity = capacity;
        this.power = power;
        this.vin = vin;
        this.color = color;
    }

    public Car getCar() {
        return car;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public List<Equipment> getAdditionalEquipment() {
        return additionalEquipment;
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

    public Transmission getGearbox() {
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
