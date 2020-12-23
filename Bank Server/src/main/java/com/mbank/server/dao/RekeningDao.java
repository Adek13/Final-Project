package com.mbank.server.dao;

import com.google.gson.Gson;
import com.mbank.server.entities.Nasabah;
import com.mbank.server.entities.Rekening;
import com.mbank.server.messaging.Producer;
import com.mbank.server.repositories.RekeningRepository;
import com.mbank.server.util.JwtToken;
import com.mbank.server.util.ResponseDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RekeningDao {

    @Autowired
    RekeningRepository rekeningRepository;

    @Autowired
    Producer producer;

    JwtToken jwtToken = new JwtToken();

    Gson gson = new Gson();

    public void getSaldo(String token){
        if(jwtToken.verifyToken(token)){
            String userString = jwtToken.decodeToken(token);
            Nasabah nasabah= gson.fromJson(userString, Nasabah.class);
            Rekening getRekening =  rekeningRepository.findByIdNasabah(nasabah.getIdNasabah()).get();
            ResponseDouble customeMessage = new ResponseDouble("Response From Bank Server", 200, "User Exist!", getRekening.getSaldo());
            String response = gson.toJson(customeMessage);
            producer.send(response);
        }else{
            ResponseDouble customeMessage = new ResponseDouble("Response From Bank Server", 403, "Forbidden", new Double(0));
            String response = gson.toJson(customeMessage);
            producer.send(response);
        }
    }

}
