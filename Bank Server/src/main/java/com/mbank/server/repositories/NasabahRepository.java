package com.mbank.server.repositories;

import com.mbank.server.entities.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NasabahRepository extends JpaRepository<Nasabah, Integer> {

    @Query("select n from Nasabah n, Rekening r where n.idNasabah = r.idNasabah and r.noRekening = ?1")
    Nasabah findByRekening (String noRekening);

}
