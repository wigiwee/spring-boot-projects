package com.elearn.app.elearn_bak.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Video {

    @Id
    private String videoId;

    private String title;

    @Column(name = "description",
        length = 1000 )
    private String desc;

    private String filePath;

    private String contentType;

}
