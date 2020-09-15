//package spring.service_layer.services;
//
//
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.springframework.stereotype.Service;
//import spring.service_layer.services.offers.OffersPopularityProducer;
//
//import javax.annotation.PostConstruct;
//
//@Service
//public class OffersPopularityService {
//
////    private OffersPopularityProducer producer;
//
//    @PostConstruct
//    public void run() {
//        producer = new OffersPopularityProducer();
//        producer.setupProducer();
//    }
//
//    public void sendMessage(Long offerID) {  new Thread(()->producer.sendMessage(offerID)).start();  }
//
//}
