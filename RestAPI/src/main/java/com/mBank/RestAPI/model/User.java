package com.mBank.RestAPI.model;

public class User {

    private String noRekening;

    private String email;

    private String noTelepon;

    private String password;

    private String idNasabah;

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(String idNasabah) {
        this.idNasabah = idNasabah;
    }
}
