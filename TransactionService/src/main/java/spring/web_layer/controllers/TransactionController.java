package spring.web_layer.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.services.TransactionService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping(value = "/createOffer",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createOffer(@RequestPart("offer") OfferDTO offerDTO, @RequestPart("images") MultipartFile images, HttpServletRequest request) throws URISyntaxException {
        return transactionService.createNewOffer(offerDTO, Collections.singletonList(images),Long.valueOf(request.getHeader("user-id"))) ?
                  ResponseEntity.created(new URI("")).body(offerDTO) :
                  ResponseEntity.status(HttpStatus.CONFLICT).body("Error creating offer");
    }

    @GetMapping("/removeOffer/{id}")
    public ResponseEntity<String> removeOffer(@PathVariable Long id, HttpServletRequest request) {
        return transactionService.removeOffer(id,Long.valueOf(request.getHeader("user-id"))) ?
                ResponseEntity.ok().body("Offer deleted") :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Not allowed");
    }
}
