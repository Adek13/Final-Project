package com.mbank.server.entities;

import javax.persistence.*;

@Entity
@Table(name = "rekening")
public class Rekening {

    @Id
    private String noRekening;

    private Double saldo;

    private int idNasabah;

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public int getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(int idNasabah) {
        this.idNasabah = idNasabah;
    }
}
