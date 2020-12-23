package com.mBank.RestAPI.util;

import com.google.gson.annotations.SerializedName;
import com.sun.xml.internal.ws.developer.Serialization;

import java.util.List;

@Serialization
public class CustomMessage {

    @SerializedName("typeMessage")
    private String typeMessage;

    @SerializedName("status")
    private String status;

    @SerializedName("payload")
    private String payload;

    @SerializedName("code")
    private int code;

    public CustomMessage() {
    }

    public CustomMessage(String typeMessage, String status, String payload) {
        this.typeMessage = typeMessage;
        this.status = status;
        this.payload = payload;
    }

    public CustomMessage(String typeMessage, String status, String payload, int code) {
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

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
