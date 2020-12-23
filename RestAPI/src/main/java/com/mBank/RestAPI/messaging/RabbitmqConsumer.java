package com.mBank.RestAPI.messaging;

import com.google.gson.Gson;
import com.mBank.RestAPI.model.Nasabah;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitmqConsumer {

    private String message;

    private String messageCorrelation;

    public String getResponse() throws IOException, TimeoutException, InterruptedException {
        message = "";
        messageCorrelation = "";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("ServerBank", false, false, false, null);
        System.out.println(" [*] Waiting for messages from database");
        channel.basicConsume("ServerBank", true, (consumerTag, delivery) -> {
            message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        }, consumerTag -> { });
        while(message.equals("")){
            Thread.sleep(0);
        }
        return message;
    }
}
