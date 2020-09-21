package spring.service_layer.services;


import com.amazonaws.services.s3.model.AmazonS3Exception;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.repository_layer.builders.OfferBuilder;
import spring.repository_layer.models.History;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.cars.FuelType;
import spring.repository_layer.models.cars.GearBox;
import spring.repository_layer.models.cars.State;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.dto.SearchDTO;
import spring.service_layer.dto.TransactionDTO;
import spring.web_layer.exceptions.OffersNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({@Autowired}))
@NoArgsConstructor
public class TransactionService {

    private RepositoryService repositoryService;
    private MailService mailService;
    private OfferBuilder offerBuilder;
    private ImagesService imagesService;

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public List<TransactionDTO> getAllOffersBySpecifiedParams(SearchDTO searchDTO) {
        List<Offer> offersList = repositoryService.offerRepository.findAllByParameters
                (
                        searchDTO.getType(),
                        searchDTO.getModel(),
                        searchDTO.getMark(),
                        searchDTO.getProduction_year_from(),
                        searchDTO.getProduction_year_to(),
                        searchDTO.getState() != null? State.valueOf(searchDTO.getState()):null,
                        searchDTO.getFuelType() != null? FuelType.valueOf(searchDTO.getFuelType()):null,
                        searchDTO.getMileage_from(),
                        searchDTO.getMileage_to(),
                        searchDTO.getLowPrice(),
                        searchDTO.getHighPrice(),
                        searchDTO.getCapacity_from(),
                        searchDTO.getCapacity_to(),
                        searchDTO.getGearbox() != null? GearBox.valueOf(searchDTO.getGearbox()):null,
                        searchDTO.getPower_from(),
                        searchDTO.getPower_to()

                ).orElse(new LinkedList<>());

        return offersList.stream().map(TransactionDTO::new).collect(Collectors.toList());
    }


    public Long createNewOffer(OfferDTO offerDTO,Long userID) {
        try {
            Offer offer = offerBuilder.createNewOffer()
                    .title(offerDTO.getTitle())
                    .tags(offerDTO.getTags())
                    .price(offerDTO.getPrice())
                    .description(offerDTO.getDescription())
                    .carType(offerDTO.getCarType())
                    .carModel(offerDTO.getModel())
                    .productionYear(offerDTO.getProduction_year())
                    .fuelType(offerDTO.getFuelType())
                    .locatedIn(offerDTO.getLocation())
                    .withMileage(offerDTO.getMileage())
                    .withCapacity(offerDTO.getCapacity())
                    .ofPower(offerDTO.getPower())
                    .withGearboxType(offerDTO.getGearbox())
                    .withVINNumber(offerDTO.getVin())
                    .atState(offerDTO.getState())
                    .additionalEquipment(offerDTO.getAdditionalEquipment())
                    .forUser(userID)
                    .build();

            repositoryService.offerRepository.save(offer);

            repositoryService.historyRepository
                    .save(new History("OFFER CREATION", offer,
                            repositoryService.userRepository.findById(userID).get()
                    ));

            new Thread(()->
            mailService.sendMail
                    (
                            repositoryService.userRepository.findById(userID).get().getEmail(),
                            MailService.NotificationType.OFFER_CREATION
                    )
            ).start();

            return offer.getId();
        } catch (Exception exc) {
            logger.info("Error creating offer");
            return null;
        }
    }

    public boolean removeOffer(Long offerId,Long userID) {
        try {

            Offer toRemove = repositoryService
                    .offerRepository
                    .findByUserIdAndOfferId(userID,offerId)
                    .orElseThrow(OffersNotFoundException::new);

            repositoryService.offerRepository.delete(toRemove);

            repositoryService.historyRepository
                            .save(new History("OFFER REMOVAL",toRemove,
                                    repositoryService.userRepository.findById(userID).get()
                            ));
            
            new Thread(()->
            mailService.sendMail
                    (
                            repositoryService.userRepository.findById(offerId).get().getEmail(),
                            MailService.NotificationType.OFFER_REMOVAL
                    )
            ).start();

            return true;
        } catch (Exception exc) {
            logger.error("Couldn't remove offer");
            return false;
        }
    }

    public TransactionDTO getOfferById(Long id) throws OffersNotFoundException {
        return repositoryService.offerRepository.findById(id).map(TransactionDTO::new).orElseThrow(OffersNotFoundException::new);
    }
}
