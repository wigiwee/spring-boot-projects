package com.elearn.app.elearn_bak.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseDto {

    private String id;

    @NotEmpty
    @Size(min = 5, max = 20)
    private String title;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String shortDesc;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String longDesc;

    private boolean live = false;

    private double price;

    private double discount;

    private Date createdDate;

    private List<VideoDto> videos = new ArrayList<>();

    private List<CategoryDto> categoryList = new ArrayList<>();
}