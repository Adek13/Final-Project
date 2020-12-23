package com.mBank.RestAPI.messaging;

import com.mBank.RestAPI.model.Nasabah;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitmqProducer {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    Logger logger;

    public void send(Nasabah nasabah){
        amqpTemplate.convertAndSend(nasabah);
        logger.info("Data Sent");
    }

}
