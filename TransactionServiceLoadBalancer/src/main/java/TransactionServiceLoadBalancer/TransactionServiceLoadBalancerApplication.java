package TransactionServiceLoadBalancer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@EnableZuulProxy
@EnableDiscoveryClient
@RibbonClient(name = "server", configuration = RibbonConfiguration.class)
@SpringBootApplication
public class TransactionServiceLoadBalancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceLoadBalancerApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate template(){return new RestTemplate();}


}
