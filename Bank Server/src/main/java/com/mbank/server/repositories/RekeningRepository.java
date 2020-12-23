package com.mbank.server.repositories;

import com.mbank.server.entities.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RekeningRepository extends JpaRepository<Rekening,String> {

    Optional<Rekening> findByIdNasabah(int idNasabah);

}
