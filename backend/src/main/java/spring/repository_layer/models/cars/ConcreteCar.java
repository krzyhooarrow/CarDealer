package spring.repository_layer.models.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import spring.repository_layer.models.Offer;

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
    private Car car;

    @OneToOne
    private FuelType fuelType;
    private String country;

    @ElementCollection
    private List<String> additionalEquipment;
    private String location_country;
    private String location_city;

    @OneToOne
    private Offer offer;

}
