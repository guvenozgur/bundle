package io.guvenozgur.bundle.scheduler.service;

import io.guvenozgur.bundle.model.RandomData;

public interface IRandomDataTransmitter {
    void sendData(RandomData data);
    void connect();

}
