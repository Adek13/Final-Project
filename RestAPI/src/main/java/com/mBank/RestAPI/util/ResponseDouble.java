package com.mBank.RestAPI.util;

import com.google.gson.annotations.SerializedName;
import com.sun.xml.internal.ws.developer.Serialization;

@Serialization
public class ResponseDouble {

    @SerializedName("typeMessage")
    private String typeMessage;

    @SerializedName("status")
    private String status;

    @SerializedName("payload")
    private Double payload;

    @SerializedName("code")
    private int code;


    public ResponseDouble(String typeMessage, String status, Double payload, int code) {
        this.typeMessage = typeMessage;
        this.status = status;
        this.payload = payload;
        this.code = code;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPayload() {
        return payload;
    }

    public void setPayload(Double payload) {
        this.payload = payload;
    }
}
