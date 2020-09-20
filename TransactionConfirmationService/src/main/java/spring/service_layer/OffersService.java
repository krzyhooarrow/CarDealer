package spring.service_layer;


import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spring.Main;
import spring.service_layer.offers.Observable;
import spring.service_layer.offers.OffersConsumer;
import spring.service_layer.offers.Subscriber;

import javax.annotation.PostConstruct;

@Service
public class OffersService implements Subscriber {

    private static final Logger logger = LoggerFactory.getLogger(OffersService.class);

    @PostConstruct
    public void run(){
        OffersConsumer consumer = new OffersConsumer();
        consumer.subscribe(this);
        new Thread(consumer::run).start();
    }


    @Override
    public void sendMessage(ConsumerRecords<Long, String> records) {
        records.forEach(record -> {
            logger.error( record.key() +  record.value() +  record.partition() + record.offset());
            // poprostu offer.get z db i dodanie gdzies
        });
    }
}
