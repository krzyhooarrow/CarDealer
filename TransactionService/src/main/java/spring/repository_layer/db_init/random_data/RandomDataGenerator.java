package spring.repository_layer.db_init.random_data;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import spring.repository_layer.db_init.CarService;
import spring.repository_layer.db_init.DBInitializer;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.*;
import spring.repository_layer.repositories.UserRepository;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.services.KafkaService;
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
    private KafkaService kafkaService;
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
    private static final Logger logger = LoggerFactory.getLogger(DBInitializer.class);


    public User createTestUser() {
        return userRepository.findByUsername("test_user").orElse(
                userRepository.save(new User("test_user", "$2a$10$HlXIjjQyM//Uho9ZRPwiJOlzOxSIp7y2J1LWuKHo6RmBaugGtyG0C",
                        "test@user.com", this.generateRandomDigit(9))));

    }

    public void initializeRandomData(int offersSize) {
        logger.info("STARTING TO INITIALIZE RANDOM DATA");
        new Thread(() -> {
            User user = createTestUser();
            int counter = 0;
            List<Car> listOfSpecifiedTypes;
            for (int i = 0; i < offersSize; i++) {

                if (100 * i / offersSize > counter) {
                    counter = 100 * i / offersSize;
                    logger.info("INITIALIZED ALREADY " + counter + "% OF DATA");
                }
                Car car = (listOfSpecifiedTypes = service.carRepository.findAll()).get(generator.nextInt(listOfSpecifiedTypes.size()));

                ConcreteCar concreteCar = carService.
                        addNewConcreteCar(car,
                                State.values()[generator.nextInt(State.values().length)],
                                Transmission.values()[generator.nextInt(Transmission.values().length)],
                                FuelType.values()[generator.nextInt(FuelType.values().length)],
                                Arrays.stream(Equipment.values()).filter(willBePresent -> generator.nextBoolean()).collect(Collectors.toList()),
                                generator.nextInt(maxMileage - minMileage + 1) + minMileage,
                                generator.nextInt((maxCapacity - minCapacity + 1) * 5) * capacityDistances + minCapacity,
                                generator.nextInt(maxPower - minPower + 1) + minPower,
                                generateRandomDigit(11),
                                Color.values()[generator.nextInt(Color.values().length)].toString()
                        );

                kafkaService.notifyOfferCreation(new OfferDTO(carService.addNewOfferBasedOnConcreteCar(concreteCar, generator.nextInt(maxPrice - minPrice + 1) + minPrice,
                        Description.values()[generator.nextInt(Description.values().length)].toString(),
                        new LinkedList<>(),
                        user,
                        car.getModel().getCarMark().getMark() + " " + car.getModel().getModel(),
                        Tag.values()[generator.nextInt(Tag.values().length)].toString()))
                );
            }
            logger.info("RANDOM DATA INIT HAS STOPPED");
        }).start();
    }

    private String generateRandomDigit(int length) {
        StringBuilder randomDigit = new StringBuilder().append(generator.nextInt(9) + 1);
        for (int i = 0; i < length - 1; i++) randomDigit.append(generator.nextInt(10));
        return randomDigit.toString();
    }


}
