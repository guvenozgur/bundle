package io.guvenozgur.bundle.scheduler.service;

import io.guvenozgur.bundle.model.RandomData;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RandomNumberDispatcherImplTest {

    @InjectMocks
    RandomNumberDispatcherImpl randomNumberDispatcher;

    @Mock
    IRandomDataTransmitter randomDataTransmitter;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateRandomData() {
        ArgumentCaptor<RandomData> captor = ArgumentCaptor.forClass(RandomData.class);
        randomNumberDispatcher.generateRandomData();
        verify(randomDataTransmitter, times(5)).sendData(captor.capture());;

        List<RandomData> data=  captor.getAllValues();
        Assertions.assertTrue(data.size() == 5);

        for(RandomData randomData: data) {
            Assertions.assertTrue(randomData.getHash().length() == 2);
            Assertions.assertTrue(randomData.getRandomVal() <= 100);
            Assertions.assertTrue(randomData.getRandomVal() >= 0);
        }
    }
}
