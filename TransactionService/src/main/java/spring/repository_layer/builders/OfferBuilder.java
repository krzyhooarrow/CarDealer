package spring.repository_layer.builders;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.*;
import spring.repository_layer.repositories.CarModelRepository;
import spring.repository_layer.repositories.CarTypeRepository;
import spring.repository_layer.repositories.FuelTypeRepository;
import spring.repository_layer.repositories.UserRepository;
import spring.service_layer.services.CarService;
import spring.service_layer.services.RepositoryService;

import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
//@AllArgsConstructor(onConstructor = @__({@Autowired}))
@NoArgsConstructor
public class OfferBuilder {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private CarService carService;


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


    public OfferBuilder price(@NonNull int price) {
        this.price = price;
        return this;
    }

    public OfferBuilder description(@NonNull String description) {
        this.description = description;
        return this;
    }

    public OfferBuilder putImages(List<String> image) {
        this.image = image == null ? new LinkedList<>() : image;
        return this;
    }

    public OfferBuilder forUser(@NonNull String user) {
        this.user = repositoryService.userRepository.findByUsername(user).orElseThrow();
        return this;
    }

    public OfferBuilder carType(@NonNull String carType) {
        this.type = repositoryService.carTypeRepository.findByCarType(carType).orElseThrow();
        return this;
    }

    public OfferBuilder carModel(@NonNull String carModel) {
        this.model = repositoryService.carModelRepository.findAllByModel(carModel).orElseThrow();
        return this;
    }

    public OfferBuilder productionYear(@NonNull Integer production_year) {
        this.production_year = production_year;
        return this;
    }

    public OfferBuilder fuelType(@NonNull String fuelType) {
        Arrays.stream(FuelTypeEnum.values()).forEach(value ->
        {
            if (value.name().equals(fuelType)) this.fuelType = value;
        });
        return this;
    }

    public OfferBuilder country(@NonNull String country) {
        this.country = country;
        return this;
    }

    public OfferBuilder locationCountry(@NonNull String location_country) {
        this.location_country = location_country;
        return this;
    }

    public OfferBuilder locationCity(@NonNull String location_city) {
        this.location_city = location_city;
        return this;
    }

    public OfferBuilder additionalEquipment(List<String> eq){this.additionalEquipment = eq == null? new LinkedList<>() : eq; return this;}

    public Offer build() {
        Car car = carService.addNewCarDefinition(production_year, model, type);

        ConcreteCar concreteCar = carService.addNewConcreteCar(car, fuelType, country, additionalEquipment, location_country, location_city);

        return new Offer(concreteCar, price, description, image, user);
    }


}
