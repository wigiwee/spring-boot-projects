package com.elearn.app.elearn_bak.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearn.app.elearn_bak.entities.Role;
import java.util.List;


@Repository
public interface RoleRepo extends JpaRepository<Role, String> {

    Optional<Role> findByRoleName(String roleName);

}
