package com.elearn.app.elearn_bak.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.elearn.app.elearn_bak.entities.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

public class CategoryDto {

    private String id;      //string primary key is better in security viewpoint

    private String title;

    private String desc;

    private Date addedDate;

    private String bannerPath;

}
