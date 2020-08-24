package spring.repository_layer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import spring.repository_layer.models.cars.Car;

import javax.persistence.*;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@NonNull
public class Offer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Car car;
    private Integer price;
    private String description;

    @ElementCollection
    private List<String> image;

    @ManyToOne
    private User user;

}
