package com.elearn.app.elearn_bak.dtos;

import java.util.List;

import lombok.Data;

@Data
public class CustomPageResponse<T> {

    private int pageSize;

    private int pageNumber;

    private long totalElements;
    
    private boolean isLast;
    
    private int totalPages;

    private List<T> content;
    
}
