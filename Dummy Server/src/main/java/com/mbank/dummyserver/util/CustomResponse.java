package com.mbank.dummyserver.util;

public class CustomResponse {

    private int code;

    private String response;

    private String status;

    public CustomResponse(int code, String response, String status) {
        this.code = code;
        this.response = response;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
