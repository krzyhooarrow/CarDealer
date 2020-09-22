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
public class CarMake {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    private String carMake;

    public CarMake(String carMark) {  this.carMake = carMark;  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return carMake;
    }

    public void setMark(String mark) {
        this.carMake = mark;
    }

    @Override
    public String toString() {
        return "CarMark{" +
                "mark='" + carMake + '\'' +
                '}';
    }
}
