package com.elearn.app.elearn_bak.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("longDescription")
    @NotEmpty
    @Size(min = 1, max = 100)
    private String longDesc;

    private boolean live = false;

    private double price;

    private double discount;

    @JsonFormat(
        shape = JsonFormat.Shape.STRING, 
        pattern = "yyyy-MM-dd", timezone = "GMT")    //to format the date    //will receive date in this format
    private Date createdDate;

    @JsonIgnore
    private String banner;

    @JsonIgnore
    private String bannerContentType;

    private List<VideoDto> videos = new ArrayList<>();

    private List<CategoryDto> categoryList = new ArrayList<>();

}