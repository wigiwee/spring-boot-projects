package com.elearn.app.elearn_bak.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearn.app.elearn_bak.entities.Course;
import com.elearn.app.elearn_bak.entities.Video;

@Repository
public interface VideoRepo extends JpaRepository<Video, String> {

    Optional<Video> findByTitle(String title);


}
