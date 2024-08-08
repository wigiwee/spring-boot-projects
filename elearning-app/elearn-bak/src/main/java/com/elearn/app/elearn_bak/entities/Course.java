package com.elearn.app.elearn_bak.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
}
