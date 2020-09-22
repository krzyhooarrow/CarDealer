package spring.repository_layer.db_init;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.*;
import spring.repository_layer.repositories.UserRepository;
import spring.service_layer.services.RepositoryService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Component
@Lazy
@Scope("singleton")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class RandomDataGenerator {

    private RepositoryService service;
    private CarService carService;
    private UserRepository userRepository;
    private final Random generator = new Random();
    private final int minMileage = 10000;
    private final int maxMileage = 300000;
    private final int minPrice = 3000;
    private final int maxPrice = 200000;
    private final int maxCapacity = 5;
    private final float capacityDistances = 0.2f;
    private final int minCapacity = 1;
    private final int minPower = 50;
    private final int maxPower = 800;
    private final long minVIN = 10000000000L;



    public User createTestUser(){
        return userRepository.save(new User("TEST_USER","$2a$10$HlXIjjQyM//Uho9ZRPwiJOlzOxSIp7y2J1LWuKHo6RmBaugGtyG0C",
                "TEST@TEST.COM","997112996"));

    }

    public void initializeRandomData(int offersSize) {
        User user = createTestUser();
        int counter; List listOfSpecifiedTypes;
        for (int i = 0 ; i < offersSize; i ++){
            Car car = (Car) (listOfSpecifiedTypes = service.carRepository.findAll()).get(generator.nextInt(listOfSpecifiedTypes.size()));

            ConcreteCar concreteCar = carService.
                    addNewConcreteCar(
                            car,
                            State.values()[generator.nextInt(State.values().length)],
                            Transmission.values()[generator.nextInt(Transmission.values().length)],
                            FuelType.values()[generator.nextInt(FuelType.values().length)],
                            Arrays.stream(Equipment.values()).filter(willBePresent -> generator.nextBoolean()).collect(Collectors.toList()),
                            generator.nextInt(maxMileage-minMileage+1)+minMileage,
                            generator.nextInt((maxCapacity-minCapacity+1)*5)*capacityDistances+minCapacity,
                            generator.nextInt(maxPower-minPower+1)+minPower,
                            String.valueOf(generator.nextDouble()*minVIN+minVIN),
                            "BLACK"
                            );

            carService.addNewOfferBasedOnConcreteCar(concreteCar,generator.nextInt(maxPrice-minPrice+1)+minPrice,
                    "RANDOM OFFER DESCRIPTION",new LinkedList<>(),user
                    );

        }
    }
}
