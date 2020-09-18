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
//@JsonSerialize(using = CarSerializer.class)
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
//    @JsonIgnore
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

    public CarType getType() {
        return type;
    }

    public CarModel getModel() {
        return model;
    }

    public Integer getProduction_year() {
        return production_year;
    }

}
