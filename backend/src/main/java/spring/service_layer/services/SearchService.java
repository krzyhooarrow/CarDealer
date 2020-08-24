package spring.service_layer.services;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.*;
import spring.repository_layer.repositories.CarRepository;
import spring.repository_layer.repositories.OfferRepository;
import spring.service_layer.dto.OfferDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({ @Autowired }))
public class SearchService {

    private static OfferRepository offerRepository;
    private static CarRepository carRepository;

    public static List<Offer> getAllOffersBySpecifiedParams(CarType carType, CarMark carMark, List<String> additionalEq,
                                                     CarModel carModel, Integer productionYear, FuelType fuelType,
                                                     String productionCountry, String locationCountry, String locationCity){

        return carRepository.findAllByParameters(carType,carMark,additionalEq,carModel,productionYear,fuelType,productionCountry,locationCountry,locationCity)
                .stream()
                .map(car -> offerRepository.findByCar(car))
                .collect(Collectors.toList());
    }
}
