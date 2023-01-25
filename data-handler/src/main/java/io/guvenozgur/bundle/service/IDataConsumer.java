package io.guvenozgur.bundle.service;

import io.guvenozgur.bundle.model.RandomData;

public interface IDataConsumer {

    void send(RandomData data);
}
