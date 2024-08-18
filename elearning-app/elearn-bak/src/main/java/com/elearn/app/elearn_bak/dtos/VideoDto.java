package com.elearn.app.elearn_bak.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VideoDto {

    private String id;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String title;

    @NotEmpty
    @Size(min = 10, max = 200)
    private String desc;

    private String filePath;

    private String contentType;

}
