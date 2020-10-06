package spring.service_layer.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.Offer;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.kafka.MessagesProducer;

import javax.annotation.PostConstruct;

@Service
@Lazy
@Scope("singleton")
public class KafkaService {

    private MessagesProducer producer;
    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);
    private final String OFFER_TOPIC="offer-topic";


    @PostConstruct
    public void initializeKafkaProducer(){   producer = new MessagesProducer().setupProperties();  }

    public void notifyOfferCreation(OfferDTO offer) {
        try {
            producer.publishMessage(OFFER_TOPIC, "new-offer", offer);
        }catch (Exception e){
            logger.error("Error putting message to Kafka due to " + e.getMessage());
        }
    }
}
