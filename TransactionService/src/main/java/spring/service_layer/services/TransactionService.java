package spring.service_layer.services;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.repository_layer.builders.OfferBuilder;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.FuelTypeEnum;
import spring.repository_layer.repositories.CarRepository;
import spring.repository_layer.repositories.ConcreteCarRepository;
import spring.repository_layer.repositories.OfferRepository;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.dto.SearchDTO;

import javax.transaction.Transaction;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({ @Autowired }))
@NoArgsConstructor
public class TransactionService {

    private RepositoryService repositoryService;

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public List<Offer> getAllOffersBySpecifiedParams(SearchDTO searchDTO){

        logger.error(searchDTO.toString());

        return repositoryService.offerRepository.findAllByParameters
                (
                searchDTO.getType(),
                searchDTO.getModel(),
                searchDTO.getMark(),
                searchDTO.getProduction_year(),
                FuelTypeEnum.valueOf(searchDTO.getFuelType()),
                searchDTO.getCountry(),
                searchDTO.getLocation_country(),
                searchDTO.getLocation_city(),
                searchDTO.getLowPrice(),
                searchDTO.getHighPrice()
                ).orElse(new LinkedList<>());

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean createNewOffer(OfferDTO offerDTO , String username){

        Offer offer = new OfferBuilder()
                .price(offerDTO.getPrice())
                .description(offerDTO.getDescription())
                .putImages(offerDTO.getImage())
                .carModel(offerDTO.getModel())
                .carType(offerDTO.getCarType())
                .productionYear(offerDTO.getProduction_year())
                .fuelType(offerDTO.getFuelType())
                .country(offerDTO.getCountry())
                .additionalEquipment(offerDTO.getAdditionalEquipment())
                .locationCountry(offerDTO.getLocation_country())
                .locationCity(offerDTO.getLocation_city())
                .forUser(username)
                .build();

        repositoryService.offerRepository.save(offer);

        return true;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean removeOffer(Long id, String user) {
        List<Offer> userOffers = repositoryService.offerRepository
         .findAllByUser(
                repositoryService.userRepository.findByUsername(user).orElseThrow()
         ).orElseThrow();

        Offer toRemove = userOffers.stream().filter(offer -> offer.getId() == id).findAny().orElseThrow();

        repositoryService.offerRepository.delete(toRemove);

        return true;
    }


}
