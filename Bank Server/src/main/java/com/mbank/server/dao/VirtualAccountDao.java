package com.mbank.server.dao;

import com.google.gson.Gson;
import com.mbank.server.entities.Mutasi;
import com.mbank.server.entities.Nasabah;
import com.mbank.server.entities.Rekening;
import com.mbank.server.messaging.Consumer;
import com.mbank.server.messaging.Producer;
import com.mbank.server.repositories.MutasiRepository;
import com.mbank.server.repositories.RekeningRepository;
import com.mbank.server.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeoutException;

@Service
public class VirtualAccountDao {

    @Autowired
    Producer producer;

    @Autowired
    Consumer consumer;

    @Autowired
    RekeningRepository rekeningRepository;

    @Autowired
    MutasiRepository mutasiRepository;

    JwtToken jwtToken = new JwtToken();

    Gson gson = new Gson();

    public void getVirtualAccount(String request) throws InterruptedException, TimeoutException, IOException {
        RequestVa requestVa = new Gson().fromJson(request, RequestVa.class);
        if(jwtToken.verifyToken(requestVa.getToken())){
            CustomPayloadToDummy customPayloadToDummy = new CustomPayloadToDummy(requestVa.getType(), requestVa.getVirtualAccount());
            String requests = new Gson().toJson(customPayloadToDummy);
            producer.sendToDummy(requests);
            String response = consumer.basicConsume();
            ResponseFromDummy responseFromDummy = new Gson().fromJson(response, ResponseFromDummy.class);
            CustomMessage customeMessage = new CustomMessage("Response From Bank Server", responseFromDummy.getCode(), responseFromDummy.getStatus(), responseFromDummy.getResponse());
            String responses = gson.toJson(customeMessage);
            producer.send(responses);
        }else{
            CustomMessage customeMessage = new CustomMessage("Response From Bank Server", 403, "Forbidden", "Failed!, Not Authorized!");
            String response = gson.toJson(customeMessage);
            producer.send(response);
        }
    }

    public void topUp(String request) throws InterruptedException, TimeoutException, IOException {
        RequestVa requestVa = new Gson().fromJson(request, RequestVa.class);
        if(jwtToken.verifyToken(requestVa.getToken())){
            Nasabah nasabah = new Gson().fromJson(jwtToken.decodeToken(requestVa.getToken()), Nasabah.class);
            Rekening rekening = rekeningRepository.findByIdNasabah(nasabah.getIdNasabah()).get();
            if (rekening.getSaldo() > requestVa.getJumlah()){
                CustomPayloadToDummy customPayloadToDummy = new CustomPayloadToDummy(requestVa.getType(), requestVa.getVirtualAccount(), requestVa.getJumlah());
                String requests = new Gson().toJson(customPayloadToDummy);
                producer.sendToDummy(requests);
                String response = consumer.basicConsume();
                ResponseFromDummy responseFromDummy = new Gson().fromJson(response, ResponseFromDummy.class);
                if (responseFromDummy.getCode()==200) {
                    Double total = rekening.getSaldo() - customPayloadToDummy.getJumlah() - 2000;
                    rekening.setSaldo(total);
                    rekeningRepository.save(rekening);
                    Mutasi mutasi = new Mutasi(rekening.getNoRekening(), customPayloadToDummy.getJumlah() + 2000, "Top Up E-Wallet", new Timestamp(System.currentTimeMillis()));
                    mutasiRepository.save(mutasi);
                }
                CustomMessage customeMessage = new CustomMessage("Response From Bank Server", responseFromDummy.getCode(), responseFromDummy.getStatus(), responseFromDummy.getResponse());
                String responses = gson.toJson(customeMessage);
                producer.send(responses);
            }else{
                CustomMessage customeMessage = new CustomMessage("Response From Bank Server", 406, "Not Acceptable", "Maaf, Saldo Anda Tidak Cukup!");
                String responses = gson.toJson(customeMessage);
                producer.send(responses);
            }

        }else{
            CustomMessage customeMessage = new CustomMessage("Response From Bank Server", 403, "Forbidden", "Failed!, Not Authorized!");
            String response = gson.toJson(customeMessage);
            producer.send(response);
        }
    }

}
