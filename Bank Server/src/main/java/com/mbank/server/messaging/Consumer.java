package com.mbank.server.messaging;

import com.google.gson.Gson;
import com.mbank.server.dao.*;
import com.mbank.server.util.CustomMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Component
public class Consumer {

    @Autowired
    UserDao userDao;

    @Autowired
    NasabahDao nasabahDao;

    @Autowired
    RekeningDao rekeningDao;

    @Autowired
    VirtualAccountDao virtualAccountDao;

    @Autowired
    MutasiDao mutasiDao;

    @Autowired
    Producer producer;

    private String response;

    @RabbitListener(queues = "RestApi")
    public void ApiRequest(Message message) throws InterruptedException, IOException, TimeoutException {
        String request = new String(message.getBody(), StandardCharsets.UTF_8);
        CustomMessage requestMessage = new Gson().fromJson(request, CustomMessage.class);
        switch (requestMessage.getTypeMessage()){
            case "Register" :
                userDao.register(requestMessage.getPayload());
                break;
            case "Login" :
                userDao.login(requestMessage.getPayload());
                break;
            case "getNasabah" :
                nasabahDao.getNasabah(requestMessage.getPayload());
                break;
            case "getSaldo" :
                rekeningDao.getSaldo(requestMessage.getPayload());
                break;
            case "getVirtualAccount" :
                virtualAccountDao.getVirtualAccount(requestMessage.getPayload());
                break;
            case "topUp" :
                virtualAccountDao.topUp(requestMessage.getPayload());
                break;
            case "getMutasi":
                mutasiDao.getMutasi(requestMessage.getPayload());
                break;
            default:
                break;
        }
    }

    public String basicConsume() throws IOException, TimeoutException, InterruptedException {
        response = "";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("dummyserver", false, false, false, null);
        System.out.println(" [*] Waiting for messages from database");
        channel.basicConsume("dummyserver", true, (consumerTag, delivery) -> {
            response = new String(delivery.getBody(), StandardCharsets.UTF_8);
        }, consumerTag -> { });
        while(response.equals("")){
            Thread.sleep(0);
        }
        return response;
    }

}