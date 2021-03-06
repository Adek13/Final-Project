package com.mBank.RestAPI.controller;

import com.google.gson.Gson;
import com.mBank.RestAPI.messaging.RabbitmqConsumer;
import com.mBank.RestAPI.messaging.RabbitmqProducer;
import com.mBank.RestAPI.model.*;
import com.mBank.RestAPI.util.CustomMessage;
import com.mBank.RestAPI.util.ResponseDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {

    @Autowired
    RabbitmqConsumer consumer;

    @Autowired
    RabbitmqProducer producer;

    CustomMessage customMessage;

    private Gson gson = new Gson();

    /* Request Register User */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> doRegister(@RequestBody User body){
        try {
            String response = "";
            String reqBody = new Gson().toJson(body);
            customMessage = new CustomMessage("Register", "Send Request To Bank Server", reqBody);
            String message = new Gson().toJson(customMessage);
            producer.send(message);
            response = consumer.getResponse();
            CustomMessage responseMessage = new Gson().fromJson(response, CustomMessage.class);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Request Login User */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity doLogin(@RequestBody Login body){
        try {
            String response = "";
            String reqBody = new Gson().toJson(body);
            customMessage = new CustomMessage("Login", "Send Request To Bank Server", reqBody);
            String message = new Gson().toJson(customMessage);
            producer.send(message);
            response = consumer.getResponse();
            CustomMessage responseMessage = new Gson().fromJson(response, CustomMessage.class);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Get Nasabah Request */
    @RequestMapping(value = "/nasabah", method = RequestMethod.GET)
    public ResponseEntity getNasabah(@RequestHeader("Authorization") String token){
        try {
            String response = "";
            customMessage = new CustomMessage("getNasabah", "Send Request To Bank Server", token);
            String message = new Gson().toJson(customMessage);
            System.out.println();
            producer.send(message);
            response = consumer.getResponse();
            CustomMessage responseMessage = new Gson().fromJson(response, CustomMessage.class);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Get Saldo Request */
    @RequestMapping(value = "/saldo", method = RequestMethod.GET)
    public ResponseEntity getSaldo(@RequestHeader("Authorization") String token){
        try {
            String response = "";
            customMessage = new CustomMessage("getSaldo", "Send Request To Bank Server", token);
            String message = new Gson().toJson(customMessage);
            producer.send(message);
            response = consumer.getResponse();
            ResponseDouble responseMessage = new Gson().fromJson(response, ResponseDouble.class);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Get Virtual Account */
    @RequestMapping(value = "/account/{va}", method = RequestMethod.GET)
    public ResponseEntity getVirtualAccount(@RequestHeader("Authorization") String token, @PathVariable("va") String va){
        try {
            String response = "";
            RequestVa body = new RequestVa("getVirtualAccount", token, va);
            String request = new Gson().toJson(body);
            System.out.println(request);
            customMessage = new CustomMessage("getVirtualAccount", "Send Request To Bank Server", request);
            String message = new Gson().toJson(customMessage);
            producer.send(message);
            response = consumer.getResponse();
            System.out.println(response);
            CustomMessage responseMessage = new Gson().fromJson(response, CustomMessage.class);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* POST Top Up E-Wallet */
    @RequestMapping(value = "/topup", method = RequestMethod.POST)
    public ResponseEntity doTopUp(@RequestBody RequestVa body, @RequestHeader("Authorization") String token){
        try {
            String response = "";
            body.setToken(token);
            String request = new Gson().toJson(body);
            customMessage = new CustomMessage("topUp", "Send Request To Bank Server", request);
            String message = new Gson().toJson(customMessage);
            producer.send(message);
            response = consumer.getResponse();
            System.out.println(response);
            CustomMessage responseMessage = new Gson().fromJson(response, CustomMessage.class);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* GET Mutasi berdasarkan tanggal awal dan tanggal akhir */
    @RequestMapping(value = "/mutasi", method = RequestMethod.GET)
    public ResponseEntity getVirtualAccount(@RequestHeader("Authorization") String token,
                                            @PathParam("startDate") String startDate,
                                            @PathParam("endDate") String endDate){
        try {
            String response = "";
            RequestMutasi body = new RequestMutasi(token,
                    new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
                    new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
            String request = new Gson().toJson(body);
            customMessage = new CustomMessage("getMutasi", "Send Request To Bank Server", request);
            String message = new Gson().toJson(customMessage);
            producer.send(message);
            response = consumer.getResponse();
            System.out.println(response);
            CustomMessage responseMessage = new Gson().fromJson(response, CustomMessage.class);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
