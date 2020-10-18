package spring.service_layer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.kafka.MessagesConsumer;
import spring.service_layer.kafka.MessagesProcessor;

import javax.annotation.PostConstruct;

@Service
public class KafkaService implements MessagesProcessor {

    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private PopularityService popularityService;

    @PostConstruct
    private void startKafkaService() {
        new Thread(()->
        new MessagesConsumer().setupConsumer(
                "popularity-service", "offer-topic").start(this, 1000L)
        ).start();
    }

    @Override
    public void process(Object o) {
        logger.info("New incoming message " + o.toString());
        popularityService.createNewOfferPopularity((OfferDTO) o);
    }
}
