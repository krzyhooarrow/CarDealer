package TransactionServiceLoadBalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class RibbonConfiguration {

    @Autowired  IClientConfig iClientConfig;

    @Bean public IPing ping(IClientConfig iClientConfig) {  return new PingUrl();  }


    @Bean  public IRule rule(IClientConfig iClientConfig) { return new AvailabilityFilteringRule(); }

}