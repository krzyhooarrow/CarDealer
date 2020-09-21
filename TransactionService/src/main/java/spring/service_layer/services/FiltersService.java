package spring.service_layer.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.cars.FuelType;
import spring.repository_layer.models.cars.GearBox;
import spring.repository_layer.models.cars.State;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({@Autowired}))
@NoArgsConstructor
public class FiltersService {

    private RepositoryService service;

    public List<Object> getDistinctCarTypes(){
        return service.offerRepository
                .getDistinctTypesWithCounter();
    }

    public List<Object> getDistinctCarMarks(){
        return service.offerRepository
                .getDistinctMarksWithCounter();
    }

    public List<Object> getDistinctCarModelsBasedOnMark(String mark){
        return service.offerRepository
                .getDistinctModelsBasedOnMarkWithCounter(mark);
    }

    public List<Object> getDistinctLocationCities(){
        return service.offerRepository
            .getDistinctLocationsWithCounter();
    }

    public List<String> getDistinctFuelTypes() {
        return Arrays
                .stream(FuelType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }


    public List<String> getAdditionalEquipment() {
        return service.equipmentRepository
                .getDistinctEquipmentNames()
                .orElseGet(LinkedList::new)
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public List<String> getStates() {
        return Arrays
                .stream(State.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public List<String> getGearboxes() {
        return Arrays
                .stream(GearBox.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
