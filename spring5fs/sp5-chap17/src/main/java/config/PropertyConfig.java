package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertyConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        // Resource[] locations = new Resource[2];
        // locations[0] = new ClassPathResource("db.properties");
        // locations[1] = new ClassPathResource("app.properties");
        // configurer.setLocations(locations);
        configurer.setLocations(
                new ClassPathResource("db.properties"),
                new ClassPathResource("app.properties"));
        return configurer;
    }
}
