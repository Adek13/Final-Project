package com.mbank.server.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "mutasi")
public class Mutasi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMutasi;

    private String noRekening;

    private Double jumlahMutasi;

    private String jenisMutasi;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp waktuMutasi;

    public Mutasi() {
    }

    public Mutasi(String noRekening, Double jumlahMutasi, String jenisMutasi, Timestamp waktuMutasi) {
        this.noRekening = noRekening;
        this.jumlahMutasi = jumlahMutasi;
        this.jenisMutasi = jenisMutasi;
        this.waktuMutasi = waktuMutasi;
    }

    public int getIdMutasi() {
        return idMutasi;
    }

    public void setIdMutasi(int idMutasi) {
        this.idMutasi = idMutasi;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public Double getJumlahMutasi() {
        return jumlahMutasi;
    }

    public void setJumlahMutasi(Double jumlahMutasi) {
        this.jumlahMutasi = jumlahMutasi;
    }

    public String getJenisMutasi() {
        return jenisMutasi;
    }

    public void setJenisMutasi(String jenisMutasi) {
        this.jenisMutasi = jenisMutasi;
    }

    public Timestamp getWaktuMutasi() {
        return waktuMutasi;
    }

    public void setWaktuMutasi(Timestamp waktuMutasi) {
        this.waktuMutasi = waktuMutasi;
    }
}
