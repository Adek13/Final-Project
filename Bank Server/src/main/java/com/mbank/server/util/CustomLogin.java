package com.mbank.server.util;



public class CustomLogin {
    private String typeMessage;

    private String status;

    private Object payload;

    private int code;

    public CustomLogin() {
    }

    public CustomLogin(String typeMessage, int code, String status, Object payload) {
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

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
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
