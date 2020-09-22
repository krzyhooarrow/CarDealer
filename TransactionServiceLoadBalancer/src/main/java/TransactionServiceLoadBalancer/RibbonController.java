package TransactionServiceLoadBalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

//@RestController
//public class RibbonController {

//        @Autowired
//        private LoadBalancerClient loadBalancer;

//        @GetMapping("")
//        public void doStuff() {
//            ServiceInstance instance = loadBalancer.choose("stores");
//            URI storesUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
            // ... do something with the URI
//        }
//}
