package spring.repository_layer.models.cars;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@NonNull
@Data
public class FuelType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FuelTypeEnum fuelTypeEnum;

    public FuelType(FuelTypeEnum fuelTypeEnum) {
        this.fuelTypeEnum = fuelTypeEnum;
    }
}
