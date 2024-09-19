package com.elearn.app.elearn_bak.repository;

import com.elearn.app.elearn_bak.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepo extends JpaRepository<Video, String> {

    List<Video> findByTitle(String title);


}
