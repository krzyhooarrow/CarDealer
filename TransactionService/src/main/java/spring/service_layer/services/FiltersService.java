package spring.service_layer.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({@Autowired}))
@NoArgsConstructor
public class FiltersService {

    private RepositoryService service;

    public List<Object> getDistinctCarTypes(){
        return service.offerRepository.getDistinctTypesWithCounter();
    }

    public List<Object> getDistinctCarMarks(){
        return service.offerRepository.getDistinctMarksWithCounter();
    }

    public List<Object> getDistinctCarModelsBasedOnMark(String mark){
        return service.offerRepository.getDistinctModelsBasedOnMarkWithCounter(mark);
    }

    public List<Object> getDistinctLocationCities(){
        return service.offerRepository.getDistinctLocationsWithCounter();
    }



}
