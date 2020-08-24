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
public class CarMark {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    private String mark;

    public CarMark(String carMark) {  this.mark = carMark;  }
}
