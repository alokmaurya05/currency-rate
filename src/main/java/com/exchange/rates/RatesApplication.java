package com.exchange.rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
/*This class responsible start the application.
 * */
@SpringBootApplication(scanBasePackages={"com.exchange"})
@EntityScan("com.exchange.entity")
@EnableJpaRepositories("com.exchange.repository")
public class RatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatesApplication.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
