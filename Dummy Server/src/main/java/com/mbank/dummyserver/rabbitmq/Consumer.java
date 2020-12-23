package com.mbank.dummyserver.rabbitmq;

import com.google.gson.Gson;
import com.mbank.dummyserver.model.Ewallet;
import com.mbank.dummyserver.service.EwalletService;
import com.mbank.dummyserver.util.RequestMessage;
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
    EwalletService ewalletService;

    private String response;

    @RabbitListener(queues = "BankRequest")
    public void ApiRequest(Message message) {
        String request = new String(message.getBody(), StandardCharsets.UTF_8);
        RequestMessage requestMessage = new Gson().fromJson(request, RequestMessage.class);
        switch (requestMessage.getType()){
            case "getVirtualAccount" :
                ewalletService.getVirtualAccount(requestMessage.getVirtualAccount());
                break;
            case "topUp":
                ewalletService.topUp(requestMessage.getVirtualAccount(), requestMessage.getJumlah());
                break;
            default:
                break;
        }
    }

}
