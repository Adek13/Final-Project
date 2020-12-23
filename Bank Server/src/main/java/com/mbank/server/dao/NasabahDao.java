package com.mbank.server.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbank.server.entities.Nasabah;
import com.mbank.server.entities.User;
import com.mbank.server.messaging.Producer;
import com.mbank.server.repositories.NasabahRepository;
import com.mbank.server.util.CustomMessage;
import com.mbank.server.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NasabahDao {

    @Autowired
    NasabahRepository nasabahRepository;

    @Autowired
    Producer producer;

    JwtToken jwtToken = new JwtToken();

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public void getNasabah(String token){
        if(jwtToken.verifyToken(token)){
            String userString = jwtToken.decodeToken(token);
            User user = gson.fromJson(userString, User.class);
            Nasabah getNasabah =  nasabahRepository.findById(user.getIdNasabah()).get();
            String nasabah = gson.toJson(getNasabah);
            CustomMessage customeMessage = new CustomMessage("Response From Bank Server", 200, "User Exist!", nasabah);
            String response = gson.toJson(customeMessage);
            producer.send(response);
        }else{
            CustomMessage customeMessage = new CustomMessage("Response From Bank Server", 403, "Forbidden", "Anda tidak memiliki akses!");
            String response = gson.toJson(customeMessage);
            producer.send(response);
        }
    }

}
