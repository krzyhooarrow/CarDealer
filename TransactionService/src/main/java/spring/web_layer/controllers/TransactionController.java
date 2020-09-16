package spring.web_layer.controllers;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.dto.OfferRemovalDTO;
import spring.service_layer.dto.SearchDTO;
import spring.service_layer.dto.TransactionDTO;
import spring.service_layer.services.TransactionService;
import spring.web_layer.exceptions.OffersNotFoundException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TransactionController {

    private TransactionService transactionService;

    @GetMapping("/createOffer")
    public ResponseEntity<Object> createOffer(@RequestBody OfferDTO offerDTO) throws URISyntaxException {
        return transactionService.createNewOffer(offerDTO) ?
                  ResponseEntity.created(new URI("")).body(offerDTO) :
                  ResponseEntity.status(HttpStatus.CONFLICT).body("Error creating offer");
    }

    @GetMapping("/removeOffer")
    public ResponseEntity<String> removeOffer(@RequestBody OfferRemovalDTO offerRemovalDTO) {
        return transactionService.removeOffer(offerRemovalDTO) ?
                ResponseEntity.ok().body("Offer deleted") :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Not allowed");
    }
}
