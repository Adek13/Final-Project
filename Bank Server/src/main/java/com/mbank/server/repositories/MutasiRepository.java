package com.mbank.server.repositories;

import com.mbank.server.entities.Mutasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MutasiRepository extends JpaRepository<Mutasi, Integer> {

    /* Query Get Data Mutasi Berdasarkan Tanggal dan Nomor Rekening */
    @Query("select m from Mutasi m where m.waktuMutasi >= ?1 and m.waktuMutasi <= ?2 and m.noRekening = ?3")
    List<Mutasi> findByDate(Date startDate, Date endDate, String noRekening);

}
