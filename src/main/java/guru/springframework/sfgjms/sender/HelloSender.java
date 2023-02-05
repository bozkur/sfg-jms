package guru.springframework.sfgjms.sender;


import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
@Slf4j
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final MessageConverter messageConverter;

    @Scheduled(fixedRate = 5_000)
    public void sendMessage() {
        log.info("Sending message");

        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();
        jmsTemplate.convertAndSend(JmsConfig.QUEUE_NAME, message);

        log.info("Message is sent");
    }

    @Scheduled(fixedRate = 3_000)
    public void sendAndReceiveMessage() throws JMSException {
        log.info("Sending message and waiting for reply");

        HelloWorldMessage payload = HelloWorldMessage.builder().
                id(UUID.randomUUID()).
                message("Hello!").build();
        log.info("Sending message... Then will wait for reply");
        Message reply = jmsTemplate.sendAndReceive(JmsConfig.SEND_RCV_QUEUE_NAME, session -> messageConverter.toMessage(payload, session));

        log.info("Reply is obtained: {}", reply.getBody(String.class));

    }
}
