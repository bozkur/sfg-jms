package guru.springframework.sfgjms.sender;


import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
@Slf4j
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    @Scheduled(fixedRate = 5_000)
    public void sendMessage() {
        log.info("Sending message");

        HelloWorldMessage message = new HelloWorldMessage().builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();
        jmsTemplate.convertAndSend(JmsConfig.QUEUE_NAME, message);

        log.info("Message is sent");
    }
}
