package TBorowski.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class CustomerConfig {
    @Bean
    @LoadBalanced
    public RestTemplate RestTemplate(@Value("${config.connectTimeout}") long connectTimeout,
                                     @Value("${config.readTimeout}") long readTimeout) {
        return new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout)).build();    }
}
