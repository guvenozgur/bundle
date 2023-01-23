package io.guvenozgur.bundle.scheduler.service;

import com.google.gson.Gson;
import io.guvenozgur.bundle.model.RandomData;
import io.guvenozgur.bundle.scheduler.util.SocketSessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;


import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class RandomDataTransmitterImpl implements IRandomDataTransmitter{


    @Value("${ws.endpoint}")
    String wsEndpoint;

    private static StompSession stompSession;
    private static final Gson gson = new Gson();

    @Override
    public void sendData(RandomData data) {
        try{
            stompSession.send("/app/data", gson.toJson(data));
        } catch (Exception e) {
            log.error("Socket is not ready : " + e.getMessage());
        }
    }

    @Override
    public void connect() {
        if(!isConnected()) {
            log.info("Socket is connecting...");
            List<Transport> transports = new ArrayList<>();
            transports.add(new WebSocketTransport(new StandardWebSocketClient()));
            SockJsClient sockJsClient = new SockJsClient(transports);
            WebSocketStompClient webSocketStompClient = new WebSocketStompClient(sockJsClient);
            webSocketStompClient.setMessageConverter(new StringMessageConverter());
            webSocketStompClient.setDefaultHeartbeat(new long[] { 20000, 0 });
            ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
            taskScheduler.afterPropertiesSet();
            webSocketStompClient.setTaskScheduler(taskScheduler);
            WebSocketHttpHeaders webSocketHttpHeaders = null;
            StompHeaders stompHeaders = new StompHeaders();


            try {
                SocketSessionHandler socketSessionHandler = new SocketSessionHandler();
                ListenableFuture<StompSession> future = webSocketStompClient.connect(wsEndpoint, webSocketHttpHeaders,
                        stompHeaders, socketSessionHandler);
                stompSession = future.get();
                stompSession.setAutoReceipt(true);
                stompSession.subscribe("/topic/filter", socketSessionHandler);
                log.info("Socket connection is ready...");


            } catch (Exception e) {
                log.error("Socket connection error: " + e.getMessage());
            }
        }
    }

    private boolean isConnected() {
        return stompSession !=null && stompSession.isConnected();
    }
}
