package guru.springframework.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class JmsConfig {

    public static final String QUEUE_NAME = "HelloQueue";

    @Bean
    public MessageConverter messageConverter() {
        return new MappingJackson2MessageConverter();

    }
}
