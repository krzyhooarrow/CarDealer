package spring.service_layer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.UserSubscriptions;
import spring.repository_layer.repositories.UserSubscriptionsRepository;
import spring.service_layer.exceptions.UserNotFoundException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@Scope("singleton")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class SubscriptionsService {

    private UserSubscriptionsRepository repository;

    public void subscribe(Long userID, Long offerID) {
        UserSubscriptions subscriptions = repository.findById(userID).orElse(new UserSubscriptions(userID,new HashSet<>()));
        subscriptions.getSubscribedOffers().add(offerID);
        repository.save(subscriptions);
    }

    public void unsubscribe(Long userID, Long offerID) {
        UserSubscriptions subscriptions = repository.findById(userID).orElse(new UserSubscriptions(userID,new HashSet<>()));
        subscriptions.getSubscribedOffers().remove(offerID);
        repository.save(subscriptions);
    }

    public Set<Long> getSubscribedOffersIDs(Long userID) {
       return repository.findById(userID).orElse(newUserSubscription(userID)).getSubscribedOffers();
    }

    private UserSubscriptions newUserSubscription(Long userID){
        UserSubscriptions userSubscriptions = new UserSubscriptions(userID,new HashSet<>());
        repository.save(userSubscriptions);
        return userSubscriptions;
    }



}
