package spring.repository_layer.models.cars;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
