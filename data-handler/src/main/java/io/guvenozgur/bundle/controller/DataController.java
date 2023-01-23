package io.guvenozgur.bundle.controller;

import com.google.gson.Gson;
import io.guvenozgur.bundle.model.ProducerTypes;
import io.guvenozgur.bundle.model.RandomData;
import io.guvenozgur.bundle.service.IDataProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DataController {

    @Autowired
    List<IDataProducer> producers;

    private static final Map<String, IDataProducer> producerCache = new HashMap<>();
    private static final Integer QUEUE_THRESHOLD_VALUE = 90;

    private static final Gson gson = new Gson();

    @PostConstruct
    public void initProducerCache() {
        for(IDataProducer dataProducer: producers) {
            producerCache.put(dataProducer.getType(), dataProducer);
        }
    }


    @MessageMapping("/data")
    public void dataListener(String randomData) throws Exception {
        RandomData data = gson.fromJson(randomData, RandomData.class);
        if(data.getRandomVal() > QUEUE_THRESHOLD_VALUE) {
            producerCache.get(ProducerTypes.QUEUE.getType()).send(data);
        } else {
            producerCache.get(ProducerTypes.FILE_WRITER.getType()).send(data);
        }
    }

}
