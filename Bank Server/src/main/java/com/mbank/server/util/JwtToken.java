package com.mbank.server.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtToken {

    private Algorithm algorithm = Algorithm.HMAC256("ShiftedBTPNS");

    public String createToken(String data){
        try {
            String token = JWT.create()
                    .withIssuer(data)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            exception.printStackTrace();
            return null;
        }
    }

    public Boolean verifyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            DecodedJWT jwt2 = JWT.decode(token);
            System.out.println(jwt2.getIssuer());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String decodeToken(String token){
        return JWT.decode(token).getIssuer();
    }
}
