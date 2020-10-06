package spring.web_layer.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.services.ImagesService;
import spring.service_layer.services.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TransactionController {

    private TransactionService transactionService;
    private ImagesService imagesService;


    @PostMapping(value = "/createOffer")
    public Long createOffer(@RequestBody OfferDTO offerDTO, HttpServletRequest request) { Long offerId;
        return (offerId = transactionService.createNewOffer(offerDTO,Long.valueOf(request.getHeader("user-id")))) != null ? offerId :  null;
    }

    @GetMapping("/removeOffer/{id}")
    public boolean removeOffer(@PathVariable Long id, HttpServletRequest request) {
        return transactionService.removeOffer(id,Long.valueOf(request.getHeader("user-id")));
    }

    @PostMapping(value = "/uploadImages/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean uploadImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return imagesService.uploadImage(id,file,Long.valueOf(request.getHeader("user-id")));
    }

}
