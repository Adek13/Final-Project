package com.mbank.server.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbank.server.entities.Mutasi;
import com.mbank.server.entities.Nasabah;
import com.mbank.server.entities.Rekening;
import com.mbank.server.messaging.Producer;
import com.mbank.server.repositories.MutasiRepository;
import com.mbank.server.repositories.NasabahRepository;
import com.mbank.server.repositories.RekeningRepository;
import com.mbank.server.util.CustomMessage;
import com.mbank.server.util.JwtToken;
import com.mbank.server.util.RequestMutasi;
import com.mbank.server.util.ResponseDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutasiDao {

    @Autowired
    RekeningRepository rekeningRepository;

    @Autowired
    MutasiRepository mutasiRepository;

    @Autowired
    Producer producer;

    JwtToken jwtToken = new JwtToken();

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public void getMutasi(String payload){
        RequestMutasi requestMutasi = new Gson().fromJson(payload, RequestMutasi.class);
        if(jwtToken.verifyToken(requestMutasi.getToken())){
            try {
                String stringNasabah = jwtToken.decodeToken(requestMutasi.getToken());
                Nasabah nasabah = new Gson().fromJson(stringNasabah, Nasabah.class);
                Rekening rekening = rekeningRepository.findByIdNasabah(nasabah.getIdNasabah()).get();
                List<Mutasi> listMutasi = mutasiRepository.findByDate(requestMutasi.getStartDate(), requestMutasi.getEndDate(), rekening.getNoRekening());
                String listString = new Gson().toJson(listMutasi);
                CustomMessage customeMessage = new CustomMessage("Response From Bank Server", 200, "sukses!", listString);
                String response = gson.toJson(customeMessage);
                producer.send(response);
            }catch (Exception e){
                CustomMessage customeMessage = new CustomMessage("Response From Bank Server", 404, "Not Found", "Tidak ada data!");
                String response = gson.toJson(customeMessage);
                producer.send(response);
            }
        }else{
            ResponseDouble customeMessage = new ResponseDouble("Response From Bank Server", 403, "Forbidden", new Double(0));
            String response = gson.toJson(customeMessage);
            producer.send(response);
        }
    }

}
