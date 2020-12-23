package com.mbank.server.util;

import java.util.List;

public class CustomMessage {

    private String typeMessage;

    private String status;

    private String payload;

    private int code;

    public CustomMessage(String typeMessage, int code, String status, String payload) {
        this.typeMessage = typeMessage;
        this.code = code;
        this.status = status;
        this.payload = payload;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
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

    public String getMessage() {
        return payload;
    }

    public void setMessage(String payload) {
        this.payload = payload;
    }
}
