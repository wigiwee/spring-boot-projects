package com.elearn.app.elearn_bak.repository;

import com.elearn.app.elearn_bak.entities.Category;
import com.elearn.app.elearn_bak.entities.Video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {

    List<Category> findByTitle(String title);

} 