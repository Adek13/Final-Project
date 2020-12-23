package com.mBank.RestAPI.messaging;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import com.rabbitmq.client.AMQP.BasicProperties;

@Component
public class RabbitmqProducer {

    public void send(String message){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare("RestApi", false, false, false, null);
            BasicProperties props = new BasicProperties()
                    .builder()
                    .contentType("application/json")
                    .build();
            channel.basicPublish("", "RestApi", props, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e){
            System.out.println("Gagal Mengirim Pesan : " + e);
        }
    }

}
