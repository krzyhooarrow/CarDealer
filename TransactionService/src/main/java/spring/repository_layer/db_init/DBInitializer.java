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
import spring.service_layer.services.RepositoryService;

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
    @Autowired
    private RepositoryService repositoryService;

    public void initializeCarsWithTheirMakesAndModels() {
        logger.info("STARTING TO INITIALIZE DATABASE MODELS");
        List<Car> cars = new LinkedList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(scriptsPath))) {

            paths.filter(Files::isRegularFile)
                    .forEach(fileName -> {
                                try {
                                    BufferedReader csvReader = new BufferedReader(new FileReader(fileName.toFile()));
                                    String row;
                                    int counter = 0;
                                    csvReader.readLine();
                                    while ((row = csvReader.readLine()) != null) {

                                        if ((counter + 1) / 100 > counter / 100) {
                                            logger.info("INITIALIZING DB.... ALREADY DONE " + counter / 100 + " HUNDREDS IN THIS FILE");
                                        }
                                        counter++;

                                        String[] data = row.split(",");

                                        CarMake carMake = repositoryService.carMakeRepository.save(carService.addCarMarkDefinition(data[1]));
                                        CarModel carModel = repositoryService.carModelRepository.save(carService.addModelDefinition(data[2], carMake));

                                        Arrays.asList(data).subList(3, data.length - 1).forEach(
                                                carType ->
                                                {
                                                    CarType type = repositoryService.carTypeRepository.save(carService.addCarTypeDefinition(carType.trim()));
                                                    cars.add(repositoryService.carRepository.save(carService.addNewCarDefinition(Integer.valueOf(data[0]), carModel, type)));
                                                }
                                        );
                                    }
                                } catch (Exception exception) {
                                    logger.error("Cannot read file to initiate database " + exception.getMessage());
                                }
                            }

                    );
            logger.info("FINISHED INITIALIZING DB");
        } catch (Exception er) {
            logger.error("Cannot find path to init files " + er.getMessage());
        }
    }
}
