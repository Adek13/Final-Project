package com.mBank.RestAPI.controller;

import com.mBank.RestAPI.model.Nasabah;
import com.mBank.RestAPI.repository.NasabahRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    NasabahRepository nasabahRepository;

    public static final Logger logger = (Logger) LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> doLogin(@RequestBody Nasabah nasabah){
        try {
            nasabahRepository.login(nasabah);
            return new ResponseEntity<>("Sukses!", HttpStatus.OK);
        }catch (Exception e){
            logger.warning(e.getMessage());
            return new ResponseEntity<>("Failed to proceed the trx", HttpStatus.BAD_REQUEST);
        }
    }
}
