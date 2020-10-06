package spring.service_layer.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import spring.repository_layer.models.Offer;
import spring.service_layer.dto.OfferDTO;
import spring.service_layer.kafka.serializers.OfferSerializer;

import java.util.Properties;

public class MessagesProducer {

    private KafkaProducer<String, OfferDTO> producer;

    public MessagesProducer setupProperties(){
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OfferSerializer.class.getName());
        producer = new KafkaProducer<>(properties);
        return this;
    }


    public void publishMessage(String topic, String key, OfferDTO dto){
        ProducerRecord<String, OfferDTO> record = new ProducerRecord<>(topic, key, dto);
        try {
            producer.send(record).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
