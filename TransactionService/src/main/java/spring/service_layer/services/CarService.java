package spring.service_layer.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.cars.*;

import java.util.List;
import java.util.Optional;



// THIS SERVICE IS ONLY CREATED FOR DATABASE INITIALIZE PURPOSE
@Service
@Lazy
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);
    private RepositoryService repositoryService;

    public Car addNewCarDefinition(Integer prodYear, CarModel carModel, CarType carType) {
        Optional<Car> car;
        Car car1 = null;

        if (!(car = repositoryService.carRepository.findAllByParameters(carType,carModel,prodYear)).isPresent()) {
            car1 = new Car(carType, carModel, prodYear);
            logger.info("Saving new car: " + repositoryService.carRepository.save(car1).toString());
        }

        return car.orElse(car1);
    }

    public CarModel addModelDefinition(String carModel, CarMark carMark) {
        Optional<CarModel> model;
        CarModel carModel1 = null;

        if (!(model = repositoryService.carModelRepository.findByModel(carModel)).isPresent()) {
            carModel1 = new CarModel(carModel, carMark);
            logger.info("Saving new carModel: " + repositoryService.carModelRepository.save(carModel1));
        }

        return model.orElse(carModel1);
    }

    public CarType addCarTypeDefinition(String carType) {
        Optional<CarType> carType1;
        CarType carType2 = null;

        if (!(carType1 = repositoryService.carTypeRepository.findByCarType(carType)).isPresent()) {
            carType2 = new CarType(carType);
            logger.info("Saving new carType: " + repositoryService.carTypeRepository.save(carType2).toString());
        }

        return carType1.orElse(carType2);
    }

    public CarMark addCarMarkDefinition(String carMark) {
        Optional<CarMark> carMark0;
        CarMark carMark1 = null;

        if (!(carMark0 = repositoryService.carMarkRepository.findByMark(carMark)).isPresent()) {
            carMark1 = new CarMark(carMark);
            logger.info("Saving new carMark: " + repositoryService.carMarkRepository.save(carMark1).toString());
        }

        return carMark0.orElse(carMark1);
    }

    public ConcreteCar addNewConcreteCar(Car car, FuelTypeEnum fuelTypeEnum, String country, List<Equipment> additionalEquipment, String location_country, String location_city) {
            addFuelTypeDefinition(fuelTypeEnum);

        return repositoryService.concreteCarRepository.save(new ConcreteCar(car,
                repositoryService.fuelTypeRepository.findByFuelTypeEnum(fuelTypeEnum).get()
                , country, additionalEquipment, location_country, location_city));

    }

    public Offer addNewOffer(ConcreteCar concreteCar) {
        return repositoryService.offerRepository.save(new Offer(concreteCar, 10000, "description", null, null));
    }


    public void addFuelTypeDefinition(FuelTypeEnum value) {
        if (!repositoryService.fuelTypeRepository.findByFuelTypeEnum(value).isPresent())
            repositoryService.fuelTypeRepository.save(new FuelType(value));
    }
}
