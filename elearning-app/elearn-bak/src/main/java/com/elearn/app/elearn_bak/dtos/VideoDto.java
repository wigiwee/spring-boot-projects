package com.elearn.app.elearn_bak.dtos;

import com.elearn.app.elearn_bak.entities.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class VideoDto {

    private String videoId;

    private String title;

    private String desc;

    private String filePath;

    private String contentType;

    private Course course;

}
