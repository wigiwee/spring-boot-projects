package com.elearn.app.elearn_bak.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

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

    private Date createdDate;

    @OneToMany(mappedBy = "course")
    private List<Video> videos = new ArrayList<>();

    @ManyToMany
    private List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category){
        this.categoryList.add(category);
        category.getCourses().add(this);
    }

    public void removeCategory(Category category){
        this.categoryList.remove(category);
        category.getCourses().remove(this);
    }
}
