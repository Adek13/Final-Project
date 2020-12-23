package com.mbank.server.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbank.server.entities.Nasabah;
import com.mbank.server.entities.User;
import com.mbank.server.messaging.Producer;
import com.mbank.server.repositories.NasabahRepository;
import com.mbank.server.repositories.UserRepository;
import com.mbank.server.util.CustomMessage;
import com.mbank.server.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDao {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NasabahRepository nasabahRepository;

    @Autowired
    Producer producer;

    JwtToken jwtToken = new JwtToken();

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public void register(String payload){
        User userPayload = gson.fromJson(payload, User.class);
        try {
//            Cek Nasabah berdasarkan Nomor Rekening nasabah
            Nasabah checkNasabahExist = nasabahRepository.findByRekening(userPayload.getNoRekening());
            if (checkNasabahExist!=null){
//                Cek Email Sudah Dipakai Atau Belum
                List<User> checkEmailExist = userRepository.findByEmail(userPayload.getEmail());
                if (checkEmailExist.size()==0){
//                    Cek Nasabah dengan No rekening x sudah terdaftar sebagai user atau belum
                    List<User> checkIdNasabahExist = userRepository.findByIdNasabah(checkNasabahExist.getIdNasabah());
                    if (checkIdNasabahExist.size()==0){
//                        Simpan User
                        userPayload.setIdNasabah(checkNasabahExist.getIdNasabah());
                        userRepository.save(userPayload);
                        CustomMessage customMessage = new CustomMessage("Response From Bank Server", 200, "OK", "Succeed");
                        String response = gson.toJson(customMessage, CustomMessage.class);
                        producer.send(response);
                    }else {
                        CustomMessage customMessage = new CustomMessage("Response From Bank Server", 409, "Conflict", "User Sudah Ada!");
                        String response = gson.toJson(customMessage, CustomMessage.class);
                        producer.send(response);
                    }
                }else{
                    CustomMessage customMessage = new CustomMessage("Response From Bank Server", 409, "Conflict", "Email Sudah Digunakan!");
                    String response = gson.toJson(customMessage, CustomMessage.class);
                    producer.send(response);
                }
            }
        }catch (Exception e){
            CustomMessage customMessage = new CustomMessage("Response From Bank Server", 404, "not found", "Nasabah Tidak Ditemukan!");
            String response = gson.toJson(customMessage, CustomMessage.class);
            producer.send(response);
        }
    }

    public void login(String payload){
        User userPayload = gson.fromJson(payload, User.class);
        try {
            User checkUserExist = userRepository.findByEmailPassword(userPayload.getEmail(), userPayload.getPassword());
            System.out.println(checkUserExist.getEmail());
            if (checkUserExist!=null){
                Optional<Nasabah> getNasabah = nasabahRepository.findById(checkUserExist.getIdNasabah());
                Nasabah nasabah = getNasabah.get();
                String nasabahString = gson.toJson(nasabah);
                String loginToken = jwtToken.createToken(nasabahString);
                CustomMessage customeMessage = new CustomMessage("Response From Bank Server", 200, "User Exist!", loginToken);
                String response = gson.toJson(customeMessage);
                producer.send(response);
            }else{
                CustomMessage customMessage = new CustomMessage("Response From Bank Server", 404, "not found", "User Tidak Ditemukan!");
                String response = gson.toJson(customMessage, CustomMessage.class);
                producer.send(response);
            }
        }catch (Exception e){
            e.printStackTrace();
            CustomMessage customMessage = new CustomMessage("Response From Bank Server", 404, "not found", "User Tidak Ditemukan!");
            String response = gson.toJson(customMessage, CustomMessage.class);
            producer.send(response);
        }

    }

}
