package spring.service_layer.offers;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface Subscriber {
    void sendMessage(ConsumerRecords<Long,String> records);
}
