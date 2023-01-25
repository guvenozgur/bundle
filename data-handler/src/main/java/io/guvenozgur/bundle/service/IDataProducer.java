package io.guvenozgur.bundle.service;

import io.guvenozgur.bundle.model.RandomData;

public interface IDataProducer {
    void send(RandomData randomData);
    String getType();
}
