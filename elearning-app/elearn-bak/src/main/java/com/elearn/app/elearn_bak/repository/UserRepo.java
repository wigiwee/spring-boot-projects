package com.elearn.app.elearn_bak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearn.app.elearn_bak.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    
}