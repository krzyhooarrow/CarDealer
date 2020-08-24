package spring.repository_layer.models.cars;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@NonNull
public class CarType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    private String carType;

    public CarType(String carType) {
        this.carType = carType;
    }
}
