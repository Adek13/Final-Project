package com.mbank.dummyserver.service;

import com.google.gson.Gson;
import com.mbank.dummyserver.model.Ewallet;
import com.mbank.dummyserver.rabbitmq.Producer;
import com.mbank.dummyserver.repository.EwalletRepository;
import com.mbank.dummyserver.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EwalletService {

    @Autowired
    Producer producer;

    @Autowired
    EwalletRepository ewalletRepository;

    public void getVirtualAccount(String virtualAccount){
        try {
            Ewallet getVa = ewalletRepository.findById(virtualAccount).get();
            String response = new Gson().toJson(new CustomResponse(200, getVa.getNama(), "sukses!"), CustomResponse.class);
            producer.send(response);
        }catch (Exception e){
            String response = new Gson().toJson(new CustomResponse(404, "Account Tidak Ditemukan!", "not found"), CustomResponse.class);
            producer.send(response);
        }
    }

    public void topUp(String virtualAccount, Double jumlah){
        try {
            System.out.println(virtualAccount);
            Ewallet getVa = ewalletRepository.findById(virtualAccount).get();
            Double total = getVa.getSaldo() + jumlah;
            getVa.setSaldo(total);
            ewalletRepository.save(getVa);
            String response = new Gson().toJson(new CustomResponse(200, "Top Up Berhasil!", "sukses!"), CustomResponse.class);
            producer.send(response);
        }catch (Exception e){
            String response = new Gson().toJson(new CustomResponse(404, "Gagal Top Up!", "not found"), CustomResponse.class);
            producer.send(response);
        }
    }

}
