package io.guvenozgur.bundle.scheduler.service;

import io.guvenozgur.bundle.model.RandomData;
import io.guvenozgur.bundle.scheduler.util.RandomDataGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

@Component
@Slf4j
public class RandomNumberDispatcherImpl implements IRandomNumberDispatcherService {

  @Autowired IRandomDataTransmitter randomDataTransmitter;

  private static final int REPEAT = 5;

  @Override
  public void generateRandomData() {
    for (int i = 0; i < REPEAT; i++) {
      try {
        RandomData data = RandomDataGenerator.generateRandomData();
        randomDataTransmitter.sendData(data);
      } catch (NoSuchAlgorithmException e) {
        log.error("Unknown hash algorithm!");
      } catch (Exception e) {
        log.info("Unknown error: " + e.getMessage());
      }
    }
  }
}
