package spring.repository_layer.db_init;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.*;
import spring.service_layer.services.RepositoryService;

import java.util.List;
import java.util.Optional;

@Service
@Lazy
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class CarService {

    private RepositoryService repositoryService;

    public Car addNewCarDefinition(Integer prodYear, CarModel carModel, CarType carType) {
        Optional<Car> car; Car car1 = null;
        if ((car = repositoryService.carRepository.findAllByParameters(carType,carModel,prodYear)).isEmpty())
            car1 = new Car(carType, carModel, prodYear);
        return car.orElse(car1);
    }

    public CarModel addModelDefinition(String carModel, CarMake carMake) {
        Optional<CarModel> model;  CarModel carModel1 = null;
        if ((model = repositoryService.carModelRepository.findByModel(carModel)).isEmpty())
            carModel1 = new CarModel(carModel, carMake);
        return model.orElse(carModel1);
    }

    public CarType addCarTypeDefinition(String carType) {
        Optional<CarType> carType1;  CarType carType2 = null;
        if ((carType1 = repositoryService.carTypeRepository.findByCarType(carType)).isEmpty())
            carType2 = new CarType(carType);
        return carType1.orElse(carType2);
    }

    public CarMake addCarMarkDefinition(String carMark) {
        Optional<CarMake> carMark0;  CarMake carMake1 = null;
        if ((carMark0 = repositoryService.carMarkRepository.findByCarMake(carMark)).isEmpty())
            carMake1 = new CarMake(carMark);
        return carMark0.orElse(carMake1);
    }

    public ConcreteCar addNewConcreteCar(Car car, State state, Transmission gearbox, FuelType fuelType, List<Equipment> additionalEquipment,
                                         int mileage, float capacity, int power, String vin, String color) {
        return repositoryService.concreteCarRepository
                .save(new ConcreteCar(car,state,gearbox,fuelType,additionalEquipment,mileage,capacity,power,vin,color));

    }

    public Offer addNewOfferBasedOnConcreteCar(ConcreteCar concreteCar,int price, String description, List<String> images, User user) {
        return repositoryService.offerRepository.save(new Offer(concreteCar, price, description, images, user));
    }


}
