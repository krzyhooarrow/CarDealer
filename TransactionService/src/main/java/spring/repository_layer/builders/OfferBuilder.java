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
    private static final Logger logger = LoggerFactory.getLogger(OfferBuilder.class);

    public Builder createNewOffer() {  return new Builder();   }


    public class Builder {


        private Integer price;
        private String description;
        private List<String> image;
        private User user;
        private CarType type;
        private CarModel model;
        private Integer production_year;
        private FuelTypeEnum fuelType;
        private String country;
        private List<String> additionalEquipment;
        private String location_country;
        private String location_city;



        public Builder price(@NonNull int price) {
            this.price = price;
            return this;
        }

        public Builder description(@NonNull String description) {
            this.description = description;
            return this;
        }

        public Builder putImages(List<String> image) {
            this.image = image == null ? new LinkedList<>() : image;
            return this;
        }

        public Builder forUser(@NonNull String user) throws UserNotFoundException {
            this.user = repositoryService.userRepository.findByUsername(user).orElseThrow(UserNotFoundException::new);
            return this;
        }

        public Builder carType(@NonNull String carType) throws CarTypeNotFoundException {
            this.type = repositoryService.carTypeRepository.findByCarType(carType).orElseThrow(CarTypeNotFoundException::new);
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
            Arrays.stream(FuelTypeEnum.values()).forEach(value ->
            {
                if (value.name().equals(fuelType)) this.fuelType = value;
            });
            return this;
        }

        public Builder country(@NonNull String country) {
            this.country = country;
            return this;
        }

        public Builder locationCountry(@NonNull String location_country) {
            this.location_country = location_country;
            return this;
        }

        public Builder locationCity(@NonNull String location_city) {
            this.location_city = location_city;
            return this;
        }

        public Builder additionalEquipment(List<String> eq) {
            this.additionalEquipment = eq == null ? new LinkedList<>() : eq;
            return this;
        }

        public Offer build() {
            Car car = carService.addNewCarDefinition(production_year, model, type);

            ConcreteCar concreteCar = carService.addNewConcreteCar(car, fuelType, country, additionalEquipment, location_country, location_city);

            return new Offer(concreteCar, price, description, image, user);
        }

    }
}
