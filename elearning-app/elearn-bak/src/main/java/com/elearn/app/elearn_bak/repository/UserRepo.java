package com.elearn.app.elearn_bak.repository;

import com.elearn.app.elearn_bak.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, String> {

    //load user with username/email
    Optional<User> findByEmail(String email);


}