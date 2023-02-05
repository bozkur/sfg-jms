package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class HelloMessageListener {

    @JmsListener(destination = JmsConfig.QUEUE_NAME)
    public void messageReceived(@Payload HelloWorldMessage message, @Headers MessageHeaders headers) {
      log.info("I got a message!");
      log.info("Obtained message:{}", message);
      log.info("Message time: {}", new Date(headers.getTimestamp()));
    }
}
