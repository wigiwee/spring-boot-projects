package com.elearn.app.elearn_bak.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "videos")
public class Video {

    @Id
    private String id;

    private String title;

    @Column(name = "description",
            length = 1000)
    private String desc;

    private String filePath;

    private String contentType;

    @ManyToOne
    private Course course;

}
