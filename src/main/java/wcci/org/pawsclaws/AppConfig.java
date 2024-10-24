package wcci.org.pawsclaws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for setting up application-wide beans.
 * <p>
 * This class provides beans that are shared across the application, such as
 * the {@link RestTemplate} bean for making HTTP requests.
 * </p>
 */
@Configuration
public class AppConfig {

    /**
     * Creates a {@link RestTemplate} bean to be used for making HTTP requests.
     * <p>
     * The {@link RestTemplate} is a convenient utility provided by Spring for
     * interacting with RESTful services.
     * </p>
     *
     * @return A new instance of {@link RestTemplate}.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(); // Instantiate and return a RestTemplate object
    }
}
