package com.mbank.server.util;

public class ResponseDouble {
    private String typeMessage;

    private String status;

    private Double payload;

    private int code;

    public ResponseDouble(String typeMessage, int code, String status, Double payload) {
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

    public Double getPayload() {
        return payload;
    }

    public void setPayload(Double payload) {
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
}
