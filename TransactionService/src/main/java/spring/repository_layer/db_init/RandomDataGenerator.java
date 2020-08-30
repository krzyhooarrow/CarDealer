package spring.repository_layer.db_init;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import spring.repository_layer.repositories.CarRepository;
import spring.repository_layer.repositories.EmailRepository;
import spring.repository_layer.repositories.OfferRepository;
import spring.repository_layer.repositories.UserRepository;
import spring.service_layer.services.CarService;

import java.util.Arrays;

@Lazy
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class RandomDataGenerator {

    private CarRepository carRepository;
    private OfferRepository offerRepository;
    private UserRepository userRepository;

    private static CarService carService;

    public static void generateRandomData(int offersSize, int usersSize){


    }

    private static void generateRandomCars(int offersSize){}

    private static void generateCarsDefinition(){

    }

}
