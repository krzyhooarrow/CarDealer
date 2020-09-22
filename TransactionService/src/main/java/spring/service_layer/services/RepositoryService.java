package spring.service_layer.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import spring.repository_layer.repositories.*;

@Service
@Scope("singleton")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class RepositoryService {

    public UserRepository userRepository;
    public CarTypeRepository carTypeRepository;
    public CarModelRepository carModelRepository;
    public CarMarkRepository carMakeRepository;
    public CarRepository carRepository;
    public ConcreteCarRepository concreteCarRepository;
    public EmailRepository emailRepository;
    public OfferRepository offerRepository;
    public HistoryRepository historyRepository;

}
