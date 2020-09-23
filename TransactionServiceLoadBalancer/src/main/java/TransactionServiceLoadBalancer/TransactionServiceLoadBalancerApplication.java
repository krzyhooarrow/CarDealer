package TransactionServiceLoadBalancer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@EnableDiscoveryClient
@RibbonClient(name = "server", configuration = RibbonConfiguration.class)
@SpringBootApplication
@RestController
public class TransactionServiceLoadBalancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceLoadBalancerApplication.class, args);
	}

	@Autowired private RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate template(){return new RestTemplate();}

	@GetMapping("**")
	public String invokeServer(HttpServletRequest request){
		return restTemplate.getForObject("http://transactionservice"+request.getRequestURI(),String.class);}
}
