package spring.service_layer.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.cars.Equipment;
import spring.repository_layer.models.cars.FuelType;
import spring.repository_layer.models.cars.Transmission;
import spring.repository_layer.models.cars.State;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({@Autowired}))
@NoArgsConstructor
public class FiltersService {

    private RepositoryService service;

    public List<Object> getDistinctCarTypesWithCounter(){
        return service.offerRepository
                .getDistinctTypesWithCounter();
    }

    public List<Object> getDistinctCarMarksWithCounter(){
        return service.offerRepository
                .getDistinctMakesWithCounter();
    }

    public List<Object> getDistinctCarModelsBasedOnMarkWithCounter(String mark){
        return service.offerRepository
                .getDistinctModelsBasedOnCarMakeWithCounter(mark);
    }

    public List<String> getFuelTypeFilters() {
        return Arrays
                .stream(FuelType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public List<String> getAdditionalEquipmentFilters() {
        return Arrays.stream(Equipment.values()).map(String::valueOf).collect(Collectors.toList());
    }

    public List<String> getStateFilters() {
        return Arrays
                .stream(State.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public List<String> getGearboxFilters() {
        return Arrays
                .stream(Transmission.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
