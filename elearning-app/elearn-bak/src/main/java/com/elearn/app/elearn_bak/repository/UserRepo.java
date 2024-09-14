package com.elearn.app.elearn_bak.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearn.app.elearn_bak.entities.User;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, String> {
    
    //load user with username/email
    Optional<User> findByEmail(String email);

    
    
}