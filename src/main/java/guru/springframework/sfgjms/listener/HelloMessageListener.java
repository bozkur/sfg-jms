package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.QUEUE_NAME)
    public void messageReceived(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers) {
        log.info("I got a message!");
        log.info("Obtained message:{}", helloWorldMessage);
        log.info("Message time: {}", new Date(headers.getTimestamp()));
    }


    @JmsListener(destination = JmsConfig.SEND_RCV_QUEUE_NAME)
    public void recieveMessageSentReply(Message message) throws JMSException {
        HelloWorldMessage reply = HelloWorldMessage.builder().
                id(UUID.randomUUID()).
                message("World!!!").build();
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), reply);
    }
}
