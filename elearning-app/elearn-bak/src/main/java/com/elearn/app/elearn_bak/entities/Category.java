package com.elearn.app.elearn_bak.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    private String id;      //string primary key is better in security viewpoint

    @Column(nullable = false)
    private String title;

    @Column(name = "description",
            nullable = false,
            length = 2000)
    private String desc;

    private Date addedDate;

    private String bannerPath;

    @ManyToMany(mappedBy = "categoryList",
            cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        this.courses.add(course);
        course.getCategoryList().add(this);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
        course.getCategoryList().remove(this);
    }
}
