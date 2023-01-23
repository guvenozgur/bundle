package io.guvenozgur.bundle.service;

import com.google.gson.Gson;
import io.guvenozgur.bundle.model.ProducerTypes;
import io.guvenozgur.bundle.model.RandomData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQProducerImpl implements IDataProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Gson gson = new Gson();

    @Override
    public void send(RandomData randomData) {
        rabbitTemplate.convertAndSend(exchange, routingKey, gson.toJson(randomData));
    }

    @Override
    public String getType() {
        return ProducerTypes.QUEUE.getType();
    }
}
