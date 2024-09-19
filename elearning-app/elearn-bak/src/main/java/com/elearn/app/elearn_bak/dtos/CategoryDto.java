package com.elearn.app.elearn_bak.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CategoryDto {

    private String id;      //string primary key is better in security viewpoint

    // @Pattern(regexp = "")    //enter regex expression 
    @NotEmpty(message = "title is required")        //validating title field before saving it to the db
    @Size(min = 3, max = 50, message = "title must be between 3 and 50")
    private String title;

    @NotEmpty(message = "description is required")
    private String desc;

    private Date addedDate;

    @JsonIgnore
    private String bannerPath;

}
