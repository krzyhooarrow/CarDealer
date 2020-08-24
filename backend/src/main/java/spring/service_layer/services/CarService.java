package spring.service_layer.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.cars.Car;
import spring.repository_layer.models.cars.CarMark;
import spring.repository_layer.models.cars.CarModel;
import spring.repository_layer.models.cars.CarType;
import spring.repository_layer.repositories.CarMarkRepository;
import spring.repository_layer.repositories.CarModelRepository;
import spring.repository_layer.repositories.CarRepository;
import spring.repository_layer.repositories.CarTypeRepository;

@Service
@Lazy
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    private CarRepository carRepository;
    private CarMarkRepository carMarkRepository;
    private CarModelRepository carModelRepository;
    private CarTypeRepository carTypeRepository;

    public void addNewCarDefinition(Integer prodYear, CarModel carModel,CarType carType){
        Car car = new Car(carType,carModel,prodYear);
        logger.info("Saving car: " +carRepository.save(car).toString());
    }

    public CarModel addModelDefinition(String carModel, CarMark carMark){
        CarModel carModel1 = new CarModel(carModel,carMark);
        logger.info("Saving carModel: " + carModel1);

        if (carModelRepository.findAll())
        carModelRepository.save(carModel1);


        return carModel1;
    }

    public CarType addCarTypeDefinition(String carType){
        CarType carType1 = new CarType(carType);
        logger.info("Saving carType: " +carTypeRepository.save(carType1).toString());

        return carType1;
    }

    public CarMark addCarMarkDefinition(String carMark){
        CarMark carMarkS = new CarMark(carMark);
        logger.info("Saving carMark: " +carMarkRepository.save(carMarkS).toString());

        return carMarkS;
    }


}
