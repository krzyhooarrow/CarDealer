package spring.repository_layer.models.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    private String model;

    @OneToOne
    private CarMark carMark;

    public CarModel(String model, CarMark carMark) {
        this.model = model;
        this.carMark = carMark;
    }
}