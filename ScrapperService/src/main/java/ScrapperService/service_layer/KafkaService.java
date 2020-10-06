package ScrapperService.service_layer;


import ScrapperService.service_layer.dto.OfferDTO;
import ScrapperService.service_layer.kafka.MessagesConsumer;
import ScrapperService.service_layer.kafka.MessagesProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class KafkaService implements MessagesProcessor {

    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);


    @PostConstruct
    private void startKafkaService() {
        new Thread(()->
        new MessagesConsumer().setupConsumer(
                "scrapper-service", "offer-topic").start(this, 1000L)
        ).start();
    }

    @Override
    public void process(Object o) {
        logger.error(((OfferDTO) o).toString());
    }
}
