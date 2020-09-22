package spring.repository_layer.builders;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.repository_layer.db_init.DBInitializer;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.*;
import spring.repository_layer.db_init.CarService;
import spring.service_layer.services.RepositoryService;
import spring.web_layer.exceptions.CarModelNotFoundException;
import spring.web_layer.exceptions.CarTypeNotFoundException;
import spring.web_layer.exceptions.UserNotFoundException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@NoArgsConstructor
public class OfferBuilder {

    private RepositoryService repositoryService;
    private CarService carService;
    private static final Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    public Builder createNewOffer() {
        return new Builder();
    }


    public class Builder {

        private String title;
        private String tags;
        private Integer price;
        private String description;
        private CarType carType;
        private CarModel model;
        private Integer production_year;
        private FuelType fuelType;
        private String location;
        private Integer mileage;
        private Float capacity;
        private Integer power;
        private Transmission gearbox;
        private String vin;
        private State state;
        private List<Equipment> additionalEquipment;
        private List<String> images;
        private User user;
        private String color;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder tags(String tags) {
            this.tags = tags;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder carType(String carType) throws CarTypeNotFoundException {
            this.carType = repositoryService.carTypeRepository.findByCarType(carType).orElseThrow(CarTypeNotFoundException::new);
            return this;
        }

        public Builder carModel(String carModel) throws CarModelNotFoundException {
            this.model = repositoryService.carModelRepository.findByModel(carModel).orElseThrow(CarModelNotFoundException::new);
            return this;
        }

        public Builder productionYear(Integer production_year) {
            this.production_year = production_year;
            return this;
        }

        public Builder fuelType(String fuelType) {
            this.fuelType = FuelType.valueOf(fuelType);
            return this;
        }

        public Builder locatedIn(String location) {
            this.location = location;
            return this;
        }

        public Builder withMileage(Integer mileage) {
            this.mileage = mileage;
            return this;
        }

        public Builder withCapacity(Float capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder ofPower(Integer power) {
            this.power = power;
            return this;
        }

        public Builder withGearboxType(String gearboxType) {
            this.gearbox = Transmission.valueOf(gearboxType);
            return this;
        }

        public Builder withVINNumber(String VIN) {
            this.vin = VIN;
            return this;
        }

        public Builder atState(String state) {
            this.state = State.valueOf(state);
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder additionalEquipment(List<String> eq) {
            this.additionalEquipment = eq == null || eq.size() == 0 ? new LinkedList<>() :
                    eq.stream().map(Equipment::fromString).collect(Collectors.toList());
            return this;
        }

        public Builder putImages(List<String> image) {
            this.images = image == null ? new LinkedList<>() : image;
            return this;
        }

        public Builder forUser(Long userId) throws UserNotFoundException {
            this.user = repositoryService.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            return this;
        }

        public Offer build() {

            Car car = carService.addNewCarDefinition(production_year, model, carType);

            ConcreteCar concreteCar = new ConcreteCar(car, state, gearbox, fuelType, additionalEquipment, mileage, capacity, power, vin, color);

            repositoryService.concreteCarRepository.save(concreteCar);

            return new Offer(concreteCar, price, description, images, user, title, tags);
        }

    }
}
