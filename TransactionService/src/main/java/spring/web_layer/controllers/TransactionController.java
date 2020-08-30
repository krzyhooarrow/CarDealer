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
import spring.service_layer.dto.SearchDTO;
import spring.service_layer.services.TransactionService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TransactionController {

    private TransactionService transactionService;
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @GetMapping("/search")
    public List<Offer> searchByParameters(@RequestBody SearchDTO params) {
    return  transactionService.getAllOffersBySpecifiedParams(params);
    }

    @GetMapping("/createOffer")
    public ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO , @RequestBody String user) throws URISyntaxException {
          return transactionService.createNewOffer(offerDTO,user) ?
                  ResponseEntity.created(new URI("")).body(offerDTO) :
                  ResponseEntity.status(HttpStatus.CONFLICT).body(offerDTO);
    }

    @GetMapping("/removeOffer")
    public ResponseEntity<String> removeOffer(@RequestBody Long id, @RequestBody String user) {
        return transactionService.removeOffer(id,user) ?
                ResponseEntity.ok().body("Offer deleted") :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Not allowed");
    }

}
