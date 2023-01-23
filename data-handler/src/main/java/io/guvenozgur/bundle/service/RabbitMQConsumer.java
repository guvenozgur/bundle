package io.guvenozgur.bundle.service;

import com.google.gson.Gson;
import io.guvenozgur.bundle.model.RandomData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer implements MessageListener {

    private static final Gson gson = new Gson();

    @Override
    public void onMessage(Message message) {
        log.info(">>> On message");
        RandomData randomData = gson.fromJson(new String(message.getBody()), RandomData.class);

        // Save to db
    }

}
