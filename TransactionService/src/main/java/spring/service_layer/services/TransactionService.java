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
import spring.service_layer.dto.OfferRemovalDTO;
import spring.service_layer.dto.SearchDTO;
import spring.web_layer.exceptions.OffersNotFoundException;
import spring.web_layer.exceptions.UserNotFoundException;

import javax.transaction.Transaction;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({@Autowired}))
@NoArgsConstructor
public class TransactionService {

    private RepositoryService repositoryService;
    private MailService mailService;
    private OfferBuilder offerBuilder;

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public List<Offer> getAllOffersBySpecifiedParams(SearchDTO searchDTO) {
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

    public boolean createNewOffer(OfferDTO offerDTO) {
        try {

            Offer offer = offerBuilder.createNewOffer()
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
                    .forUser(offerDTO.getUsername())
                    .build();

            repositoryService.offerRepository.save(offer);

            mailService.sendMail
                    (
                            repositoryService.userRepository.findByUsername(offerDTO.getUsername()).get().getEmail(),
                            MailService.NotificationType.OFFER_CREATION
                    );

            return true;
        } catch (Exception exc) {
            logger.info("Error creating offer");
            return false;
        }
    }


    public boolean removeOffer(OfferRemovalDTO offerRemovalDTO) {
        try {

            List<Offer> userOffers = repositoryService.offerRepository
                    .findAllByUser(
                            repositoryService.userRepository.findByUsername(offerRemovalDTO.getUsername()).orElseThrow(UserNotFoundException::new)
                    ).orElseThrow(OffersNotFoundException::new);

            Offer toRemove = userOffers.stream().filter(offer -> offer.getId().equals(offerRemovalDTO.getOfferId()))
                    .findFirst().orElseThrow(OffersNotFoundException::new);

            repositoryService.offerRepository.delete(toRemove);

            mailService.sendMail
                    (
                            repositoryService.userRepository.findByUsername(offerRemovalDTO.getUsername()).get().getEmail(),
                            MailService.NotificationType.OFFER_REMOVAL
                    );

            return true;
        } catch (Exception exc) {
            logger.error("Couldn't remove offer");
            return false;
        }
    }


}
