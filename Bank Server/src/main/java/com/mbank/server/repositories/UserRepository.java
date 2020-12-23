package com.mbank.server.repositories;

import com.mbank.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("FROM User WHERE email = ?1")
    List<User> findByEmail(String email);

    @Query("select u from User u, Nasabah n where u.idNasabah = n.idNasabah and u.idNasabah = ?1")
    List<User> findByIdNasabah(int idNasabah);

    @Query("select u from User u where u.email = ?1 and u.password = ?2")
    User findByEmailPassword(String email, String password);

}
