package com.mBank.RestAPI.model;

public class RequestVa {

    private String type;

    private String token;

    private String virtualAccount;

    private Double jumlah;

    public RequestVa(String type, String token, String virtualAccount) {
        this.type = type;
        this.token = token;
        this.virtualAccount = virtualAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVirtualAccount() {
        return virtualAccount;
    }

    public void setVirtualAccount(String virtualAccount) {
        this.virtualAccount = virtualAccount;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }
}
