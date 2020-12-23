package com.mBank.RestAPI.util;

import com.mBank.RestAPI.model.VirtualAccount;

public class ResponseVa {

    private String status;

    private int code;

    private String message;

    private VirtualAccount data;

    public ResponseVa(String status, int code, String message, VirtualAccount data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VirtualAccount getData() {
        return data;
    }

    public void setData(VirtualAccount data) {
        this.data = data;
    }

}
