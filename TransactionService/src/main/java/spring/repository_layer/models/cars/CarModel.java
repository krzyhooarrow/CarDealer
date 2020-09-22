package spring.repository_layer.models.cars;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String model;

    @OneToOne
    private CarMake carMake;

    public CarModel(String model, CarMake carMake) {
        this.model = model;
        this.carMake = carMake;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public CarMake getCarMark() {
        return carMake;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCarMark(CarMake carMake) {
        this.carMake = carMake;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "model='" + model + '\'' +
                ", carMark=" + carMake +
                '}';
    }
}
