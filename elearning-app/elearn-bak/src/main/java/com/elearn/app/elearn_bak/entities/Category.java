package com.elearn.app.elearn_bak.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

    @ManyToMany(mappedBy = "categoryList")
    private List<Course> courses = new ArrayList<>();
}
