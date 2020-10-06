package ScrapperService.service_layer.kafka;

import ScrapperService.service_layer.dto.OfferDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class MessagesConsumer {

    KafkaConsumer<String, OfferDTO> consumer;

    public MessagesConsumer setupConsumer(String groupId, String subscribedTopic) {
        Properties properties = new Properties();
        properties.put("group.id", groupId);
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OfferDeserializer.class.getName());

        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(subscribedTopic));
        return this;
    }

    public void start(MessagesProcessor processor, Long timeout) {
        try {
            while (true) {
                consumer.poll(timeout).forEach(record -> processor.process(record.value()));
            }
        } finally {
            consumer.close();
        }
    }


}
