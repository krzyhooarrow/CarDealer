package spring.service_layer.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.service_layer.dto.HistoryDTO;
import spring.service_layer.dto.TransactionDTO;
import spring.web_layer.exceptions.HistoryNotFoundException;
import spring.web_layer.exceptions.OffersNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({@Autowired}))
@NoArgsConstructor
public class UserService {

    private RepositoryService service;

    public List<TransactionDTO> getUserOffers(Long userID) throws OffersNotFoundException {
        return service.offerRepository
                .getOffersByUserId(userID)
                .stream()
                .flatMap(Collection::stream)
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }


    public List<TransactionDTO> getOffersByIdsList(List<Long> userIds) throws OffersNotFoundException {
        return service.offerRepository
                .getOffersList(userIds)
                .stream()
                .flatMap(Collection::stream)
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }

    public List<HistoryDTO> getUserHistory(Long userId) throws HistoryNotFoundException {
        return service.historyRepository
                .findAllByUserId(userId)
                .stream()
                .flatMap(Collection::stream)
                .map(HistoryDTO::new)
                .collect(Collectors.toList());
    }
}
