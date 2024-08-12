package com.elearn.app.elearn_bak.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CategoryDto {

    private String id;      //string primary key is better in security viewpoint

    private String title;

    private String desc;

    private Date addedDate;

    @JsonIgnore
    private String bannerPath;

}
