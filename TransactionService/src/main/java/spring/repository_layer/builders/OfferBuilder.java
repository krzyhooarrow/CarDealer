package spring.repository_layer.builders;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.*;
import spring.service_layer.services.CarService;
import spring.service_layer.services.RepositoryService;
import spring.web_layer.exceptions.CarModelNotFoundException;
import spring.web_layer.exceptions.CarTypeNotFoundException;
import spring.web_layer.exceptions.UserNotFoundException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@NoArgsConstructor
public class OfferBuilder {

    private RepositoryService repositoryService;
    private CarService carService;
    public Builder createNewOffer() {  return new Builder();   }



    public class Builder {

        private String title;
        private String tags;
        private Integer price;
        private String description;
        private CarType carType;
        private CarModel model;
        private Integer production_year;
        private FuelTypeEnum fuelType;
        private String location;
        private Integer mileage;
        private Float capacity;
        private Integer power;
        private GearBox gearbox;
        private String vin;
        private State state;
        private List<String> additionalEquipment;
        private List<String> images;
        private User user;

        public Builder title(@NonNull String title){
            this.title = title;
            return this;
        }

        public Builder tags(@NonNull String tags){
            this.tags = tags;
            return this;
        }

        public Builder price(@NonNull int price) {
            this.price = price;
            return this;
        }

        public Builder description(@NonNull String description) {
            this.description = description;
            return this;
        }

        public Builder carType(@NonNull String carType) throws CarTypeNotFoundException {
            this.carType = repositoryService.carTypeRepository.findByCarType(carType).orElseThrow(CarTypeNotFoundException::new);
            return this;
        }

        public Builder carModel(@NonNull String carModel) throws CarModelNotFoundException {
            this.model = repositoryService.carModelRepository.findByModel(carModel).orElseThrow(CarModelNotFoundException::new);
            return this;
        }

        public Builder productionYear(@NonNull Integer production_year) {
            this.production_year = production_year;
            return this;
        }

        public Builder fuelType(@NonNull String fuelType) {
            this.fuelType = FuelTypeEnum.valueOf(fuelType);
            return this;
        }

        public Builder locatedIn(@NonNull String location) {
            this.location = location;
            return this;
        }

        public Builder withMileage(@NonNull Integer mileage) {
            this.mileage = mileage;
            return this;
        }

        public Builder withCapacity(@NonNull Float capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder ofPower(@NonNull Integer power) {
            this.power = power;
            return this;
        }

        public Builder withGearboxType(@NonNull String gearboxType) {
            this.gearbox = GearBox.valueOf(gearboxType);
            return this;
        }

        public Builder withVINNumber(@NonNull String VIN) {
            this.vin = VIN;
            return this;
        }

        public Builder atState(@NonNull String state) {
            this.state = State.valueOf(state);
            return this;
        }

        public Builder additionalEquipment(List<String> eq) {
            this.additionalEquipment = eq == null ? new LinkedList<>() : eq;
            return this;
        }

        public Builder putImages(List<String> image) {
            this.images = image == null ? new LinkedList<>() : image;
            return this;
        }

        public Builder forUser(@NonNull Long userId) throws UserNotFoundException {
            this.user = repositoryService.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            return this;
        }

        public Offer build() {
            Car car = carService.addNewCarDefinition(production_year, model, carType);

            ConcreteCar concreteCar =  repositoryService.concreteCarRepository.save(
                    new ConcreteCar(car,repositoryService.
                            fuelTypeRepository.findByFuelTypeEnum(fuelType).get(),location,
                            additionalEquipment, mileage,capacity,power,gearbox,vin ,state));


            return new Offer(concreteCar, price, description, images, user,title,tags);
        }

    }
}
