package io.guvenozgur.bundle.scheduler.util;

import io.guvenozgur.bundle.scheduler.service.IRandomDataTransmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitSocket implements ApplicationRunner {

    @Autowired
    IRandomDataTransmitter randomDataTransmitter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            randomDataTransmitter.connect();
            Thread.sleep(5000);
        }
    }
}
