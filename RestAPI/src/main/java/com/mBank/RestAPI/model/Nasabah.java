package com.mBank.RestAPI.model;

import java.util.Date;

public class Nasabah {

    private int id_nasabah;

    private String nama_nasabah;

    private String tanggal_lahir;

    private String alamat;

    public int getId_nasabah() {
        return id_nasabah;
    }

    public void setId_nasabah(int id_nasabah) {
        this.id_nasabah = id_nasabah;
    }

    public String getNama_nasabah() {
        return nama_nasabah;
    }

    public void setNama_nasabah(String nama_nasabah) {
        this.nama_nasabah = nama_nasabah;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
