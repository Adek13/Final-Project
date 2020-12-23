package com.mBank.RestAPI.repository;

import com.mBank.RestAPI.messaging.RabbitmqProducer;
import com.mBank.RestAPI.model.Nasabah;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class NasabahRepository {

    @Autowired
    RabbitmqProducer rabbitmqProducer;

    @Autowired
    Logger logger;

    public void login(Nasabah nasabah){
        rabbitmqProducer.send(nasabah);
    }

}
