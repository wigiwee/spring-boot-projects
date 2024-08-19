package com.elearn.app.elearn_bak.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
    import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    public void addCourse(Course course){
        this.courses.add(course);
        course.getCategoryList().add(this);
    }

    public void removeCourse(Course course){
        this.courses.remove(course);
        course.getCategoryList().remove(this);
    }
}
