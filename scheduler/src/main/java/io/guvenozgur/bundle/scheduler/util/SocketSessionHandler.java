package io.guvenozgur.bundle.scheduler.util;

import io.guvenozgur.bundle.scheduler.service.IRandomDataTransmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SocketSessionHandler extends StompSessionHandlerAdapter {
    @Autowired
    IRandomDataTransmitter randomDataTransmitter;

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable exception) {
        System.out.println(exception.getMessage());
        try {
            Thread.sleep(5000);
            randomDataTransmitter.connect();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
