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
        Optional<Car> car;
        return ((car = repositoryService.carRepository
                .findAllByParameters(carType,carModel,prodYear)).isEmpty())? new Car(carType,carModel,prodYear) : car.get();
    }

    public CarModel addModelDefinition(String carModel, CarMake carMake) {
        Optional<CarModel> model;
        return ((model = repositoryService.carModelRepository
                .findByModel(carModel)).isEmpty())? new CarModel(carModel,carMake) : model.get();
    }

    public CarType addCarTypeDefinition(String carType) {
        Optional<CarType> carType1;
        return ((carType1 = repositoryService.carTypeRepository
                .findByCarType(carType)).isEmpty()) ? new CarType(carType) : carType1.get();
    }

    public CarMake addCarMarkDefinition(String carMark) {
        Optional<CarMake> carMark0;
        return ((carMark0 = repositoryService.carMakeRepository
                .findByCarMake(carMark)).isEmpty()) ? new CarMake(carMark) : carMark0.get();
    }

    public ConcreteCar addNewConcreteCar(Car car, State state, Transmission gearbox, FuelType fuelType, List<Equipment> additionalEquipment,
                                         int mileage, float capacity, int power, String vin, String color) {
        return repositoryService.concreteCarRepository
                .save(new ConcreteCar(car,state,gearbox,fuelType,additionalEquipment,mileage,capacity,power,vin,color));

    }

    public Offer addNewOfferBasedOnConcreteCar(ConcreteCar concreteCar,int price, String description, List<String> images, User user,String title,String tags) {
        return repositoryService.offerRepository.save(new Offer(concreteCar, price, description, images, user,title,tags));
    }


}
