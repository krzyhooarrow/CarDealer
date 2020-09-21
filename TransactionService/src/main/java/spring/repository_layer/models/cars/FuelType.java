package spring.repository_layer.models.cars;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;


public enum FuelType {
    diesel,petrol,gas,hybrid;
}
