package TransactionServiceLoadBalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


@EnableDiscoveryClient
@RibbonClient(name = "custom", configuration = RibbonConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.netflix.client.config.IClientConfig"})
public class TransactionServiceLoadBalancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceLoadBalancerApplication.class, args);
	}

}
