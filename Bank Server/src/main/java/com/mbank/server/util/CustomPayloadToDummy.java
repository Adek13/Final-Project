package com.mbank.server.util;

public class CustomPayloadToDummy {

    private String type;

    private String virtualAccount;

    private Double jumlah;

    public String getRequest() {
        return type;
    }

    public CustomPayloadToDummy(String type, String virtualAccount) {
        this.type = type;
        this.virtualAccount = virtualAccount;
    }

    public CustomPayloadToDummy(String type, String virtualAccount, Double jumlah) {
        this.type = type;
        this.virtualAccount = virtualAccount;
        this.jumlah = jumlah;
    }

    public void setRequest(String type) {
        this.type = type;
    }

    public String getVirtualAccount() {
        return virtualAccount;
    }

    public void setVirtualAccount(String virtualAccount) {
        this.virtualAccount = virtualAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }
}
