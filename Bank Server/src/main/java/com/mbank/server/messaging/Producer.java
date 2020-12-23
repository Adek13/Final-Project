package com.mbank.server.messaging;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.AMQP.BasicProperties;

import java.nio.charset.StandardCharsets;

@Component
public class Producer {

    public void send(String message){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("ServerBank", false, false, false, null);
            BasicProperties props = new BasicProperties()
                    .builder()
                    .contentType("application/json")
                    .expiration("300000")
                    .build();
            channel.basicPublish("", "ServerBank", props, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e){
            System.out.println("Gagal Mengirim Pesan : " + e);
        }
    }
    public void sendToDummy(String message){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("BankRequest", false, false, false, null);
            BasicProperties props = new BasicProperties()
                    .builder()
                    .contentType("application/json")
                    .build();
            channel.basicPublish("", "BankRequest", props, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e){
            System.out.println("Gagal Mengirim Pesan : " + e);
        }
    }


}
