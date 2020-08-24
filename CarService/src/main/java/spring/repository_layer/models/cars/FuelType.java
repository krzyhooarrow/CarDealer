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
public enum FuelType {
    diesel,petrol,gas,hybrid;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
}
