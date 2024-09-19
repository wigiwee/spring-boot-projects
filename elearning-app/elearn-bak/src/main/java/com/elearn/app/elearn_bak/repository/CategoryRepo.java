package com.elearn.app.elearn_bak.repository;

import com.elearn.app.elearn_bak.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {


} 