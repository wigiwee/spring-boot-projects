package com.elearn.app.elearn_bak.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class CourseDto {

    private String id;

    private String title;

    private String shortDesc;

    private String longDesc;

    private boolean live = false;

    private double price;

    private double discount;

    private Date createdDate;

}