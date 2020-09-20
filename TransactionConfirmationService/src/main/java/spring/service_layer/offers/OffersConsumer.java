package spring.service_layer.offers;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class OffersConsumer implements Observable{

    private final List<Subscriber> watchers = new LinkedList<>();
    private final static String TOPIC = "offers-topic";
    private final static String BOOTSTRAP_SERVERS ="localhost:9092";
    private final int pollTime = 1000;
    public boolean consumerIsRunning = true;

    private static Consumer<Long, String> getConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"offers-consumers");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        final Consumer<Long, String> consumer =new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(TOPIC));

        return consumer;
    }

    public void run() {
        final Consumer<Long, String> consumer = getConsumer();

        while (consumerIsRunning) {
            final ConsumerRecords<Long, String> consumerRecords = consumer.poll(pollTime);

            if (consumerRecords.count()==0)   continue;

            notifyWatchers(consumerRecords);
            consumer.commitAsync();
        }
        consumer.close();
    }


    @Override
    public void notifyWatchers(ConsumerRecords<Long,String> records) {
    watchers.forEach(watcher -> watcher.sendMessage(records));
    }

    @Override
    public void subscribe(Subscriber s) {
        watchers.add(s);
    }
}
