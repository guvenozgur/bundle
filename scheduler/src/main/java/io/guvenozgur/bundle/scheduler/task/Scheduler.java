package io.guvenozgur.bundle.scheduler.task;

import io.guvenozgur.bundle.scheduler.service.IRandomNumberDispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Scheduler {

    @Autowired
    IRandomNumberDispatcherService randomNumberDispatcherService;

    //@Scheduled(cron = "* * * * * *")
    @Scheduled(initialDelay = 30 * 1000, fixedDelay = 1 * 1000)
    public void start() {
        randomNumberDispatcherService.generateRandomData();
    }
}
