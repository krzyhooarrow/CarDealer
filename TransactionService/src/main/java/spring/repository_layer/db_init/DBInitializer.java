package spring.repository_layer.db_init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import spring.repository_layer.models.cars.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Component
@Lazy
@Scope("singleton")
@PropertySource("classpath:application.properties")
public class DBInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Value("${database.init.path}")
    private String scriptsPath;

    @Autowired
    private CarService carService;

    public void initializeCarsWithTheirMakesAndModels() {
        List<Car> cars = new LinkedList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(scriptsPath))) {

               paths.filter(Files::isRegularFile)
                    .forEach(fileName -> {
                                try {
                                    BufferedReader csvReader = new BufferedReader(new FileReader(fileName.toFile()));
                                    String row;

                                    csvReader.readLine();
                                    // leave first line
                                    while ((row = csvReader.readLine()) != null) {
                                        String[] data = row.split(",");

                                        CarMake carMake = carService.addCarMarkDefinition(data[1]);
                                        CarModel carModel = carService.addModelDefinition(data[2], carMake);

                                        Arrays.asList(data).subList(3, data.length - 1).forEach(
                                                carType ->
                                                {
                                                    CarType type = carService.addCarTypeDefinition(carType.trim());
                                                    cars.add(carService.addNewCarDefinition(Integer.valueOf(data[0]),carModel, type));
                                                }
                                        );
                                    }
                                } catch (Exception exception) {
                                    logger.error("Cannot read file to initiate database " + exception.getMessage());
                                }
                            }
                    );
        } catch (Exception er) {
            logger.error("Cannot find path to init files " + er.getMessage());
        }
    }
}
