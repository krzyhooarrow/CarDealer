//package spring.service_layer.services.offers;
//
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.*;
//
//import org.apache.kafka.common.serialization.LongSerializer;
//
//import org.apache.kafka.common.serialization.StringSerializer;
//
//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Properties;
//
//public class OffersPopularityProducer {
//
//    private final static String TOPIC = "offers-popularity";
//    private final static String BOOTSTRAP_SERVERS ="localhost:9092";
//    private final int pollTime = 1000;
//    public boolean consumerIsRunning = true;
//    private int recordIndex = 0;
//    private Producer<String,Long> producer;
//
//    public void setupProducer() {
//        final Properties props = new Properties();
//
//        props.put(ProducerConfig.ACKS_CONFIG, "all");
//        props.put(ProducerConfig.RETRIES_CONFIG, 0);
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, "id-1");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
//        this.producer = new KafkaProducer<>(props);
//    }
//
//    public void sendMessage(Long value) {
//        ProducerRecord<String, Long> record = new ProducerRecord<>(TOPIC, value);
//        producer.send(record).isDone();
//    }
//}
