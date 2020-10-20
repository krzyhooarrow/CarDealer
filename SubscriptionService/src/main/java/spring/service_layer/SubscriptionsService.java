package spring.service_layer;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import spring.Main;
import spring.repository_layer.models.MaxPopularityCounter;
import spring.repository_layer.models.OfferPopularity;
import spring.repository_layer.models.Type;
import spring.repository_layer.models.UserSubscriptions;
import spring.repository_layer.repositories.UserSubscriptionsRepository;
import spring.service_layer.exceptions.OfferNotFoundException;
import spring.service_layer.exceptions.UserNotFoundException;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
@Scope("singleton")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class SubscriptionsService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionsService.class);
    private UserSubscriptionsRepository repository;
    private MaxPopularityService popularityService;

    public void subscribe(Long userID, Long offerID) {
        UserSubscriptions subscriptions = repository.findById(userID).orElse(new UserSubscriptions(userID));
        if (subscriptions.getSubscribedOffers()!=null)
        subscriptions.getSubscribedOffers().add(offerID);
        else subscriptions.setSubscribedOffers(Collections.singleton(offerID));
        repository.save(subscriptions);
    }

    public void unsubscribe(Long userID, Long offerID) throws UserNotFoundException {
        UserSubscriptions subscriptions = repository.findById(userID).orElseThrow(UserNotFoundException::new);
        if (subscriptions.getSubscribedOffers()!=null)
        subscriptions.getSubscribedOffers().remove(offerID);
        repository.save(subscriptions);
    }

    public Set<Long> getSubscribedOffersIDs(Long userID) {
        Optional<UserSubscriptions> userSubscriptions;
        return (userSubscriptions = repository.findById(userID))
               .isPresent()? userSubscriptions.get().getSubscribedOffers() != null?
               userSubscriptions.get().getSubscribedOffers() : new HashSet<>(): new HashSet<>();
    }

}
