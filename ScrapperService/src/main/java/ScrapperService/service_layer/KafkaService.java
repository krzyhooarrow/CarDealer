package ScrapperService.service_layer;


import ScrapperService.service_layer.dto.OfferDTO;
import ScrapperService.service_layer.kafka.MessagesConsumer;
import ScrapperService.service_layer.kafka.MessagesProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class KafkaService implements MessagesProcessor {

    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private OfferComparatorService offerComparatorService;
    @Autowired
    private VinService vinService;


    @PostConstruct
    private void startKafkaService() {
        new Thread(()->
        new MessagesConsumer().setupConsumer(
                "scrapper-service", "offer-topic").start(this, 1000L)
        ).start();
    }

    @Override
    public void process(Object o) {
        offerComparatorService.matchOffers((OfferDTO) o,2);
        vinService.scrapCarData(((OfferDTO) o));
    }
}
