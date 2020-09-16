package spring.web_layer.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.service_layer.dto.SearchDTO;
import spring.service_layer.dto.TransactionDTO;
import spring.service_layer.services.TransactionService;
import spring.web_layer.exceptions.OffersNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/offers")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OffersController {

    private TransactionService transactionService;


    @PostMapping("/search")
    public List<TransactionDTO> searchByParameters(@RequestBody SearchDTO params) {
        return transactionService.getAllOffersBySpecifiedParams(params);
    }

    @GetMapping("/{id}")
    public TransactionDTO getOfferById(@PathVariable Long id) throws OffersNotFoundException {
        return transactionService.getOfferById(id);
    }
}
