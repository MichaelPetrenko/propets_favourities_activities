package telran.favorites.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FavConfiguration {
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	};

}
