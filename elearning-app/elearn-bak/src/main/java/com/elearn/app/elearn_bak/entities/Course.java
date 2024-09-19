package com.elearn.app.elearn_bak.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    private String id;

    private String title;

    private String shortDesc;

    private String longDesc;

    private boolean live = false;

    private double price;

    private double discount;

    private String banner;

    private String bannerContentType;

    private Date createdDate;

    @OneToMany(mappedBy = "course")
    private List<Video> videos = new ArrayList<>();

    @ManyToMany
    private List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category) {
        this.categoryList.add(category);
        category.getCourses().add(this);
    }

    public void removeCategory(Category category) {
        this.categoryList.remove(category);
        category.getCourses().remove(this);
    }
}
