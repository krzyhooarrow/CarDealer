package spring.web_layer.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.service_layer.dto.OfferRemovalDTO;
import spring.service_layer.dto.TransactionDTO;
import spring.service_layer.services.UserService;
import spring.web_layer.exceptions.OffersNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserOffersController {

    private UserService service;

    @GetMapping("/getOffers")
    public List<TransactionDTO> getOffers(HttpServletRequest request) throws OffersNotFoundException {
        return service.getUserOffers(Long.valueOf(request.getHeader("user-id")));
    }

    @PostMapping("/getSubscriptions")
    public List<TransactionDTO> getSubscribedOffer(@RequestBody List<Long> ids) throws OffersNotFoundException {
        return service.getOffersByIdsList(ids);
    }
}
