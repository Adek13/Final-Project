package com.mbank.dummyserver.controller;

import com.google.gson.Gson;
import com.mbank.dummyserver.model.Ewallet;
import com.mbank.dummyserver.repository.EwalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    EwalletRepository ewalletRepository;

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSaldo(@PathVariable("id") String virtualAccount){
        Map response = new HashMap();
        try {
            Ewallet result = ewalletRepository.findById(virtualAccount).get();
            String data = new Gson().toJson(result);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

}
