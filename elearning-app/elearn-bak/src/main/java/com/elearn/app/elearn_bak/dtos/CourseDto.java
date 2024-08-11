package com.elearn.app.elearn_bak.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private List<VideoDto> videos = new ArrayList<>();

    private List<CategoryDto> categoryList = new ArrayList<>();
}