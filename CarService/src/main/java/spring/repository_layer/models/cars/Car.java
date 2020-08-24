package spring.repository_layer.models.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@NonNull
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    private CarType type;

    @OneToOne
    private CarModel model;

    private Integer production_year;

    public Car(CarType type, CarModel model, Integer production_year) {
        this.type = type;
        this.model = model;
        this.production_year = production_year;
    }
}
