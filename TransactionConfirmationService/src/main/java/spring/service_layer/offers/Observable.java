package spring.service_layer.offers;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface Observable {

    void notifyWatchers(ConsumerRecords<Long,String> records);
    void subscribe(Subscriber s);
}
