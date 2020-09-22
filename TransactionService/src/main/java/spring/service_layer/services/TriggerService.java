package spring.service_layer.services;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.ActionType;
import spring.repository_layer.models.History;
import spring.repository_layer.models.Offer;

@Service
@Lazy
@Scope("singleton")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TriggerService {

    private RepositoryService repositoryService;
    private MailService mailService;

    public void castDatabaseUpdate(ActionType actionType, Offer offer, Long userID) {

        repositoryService.historyRepository.save(
                new History(actionType, offer,
                        repositoryService.userRepository.findById(userID).get()
                ));

        new Thread(() -> mailService.sendMail
                (
                        repositoryService.userRepository.findById(userID).get().getEmail(), actionType
                )
        ).start();
    }
}
