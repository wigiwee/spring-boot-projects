package com.elearn.app.elearn_bak.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CustomPageResponse<T> {

    private int pageSize;

    private int pageNumber;

    private long totalElements;

    private boolean isLast;

    private int totalPages;

    private List<T> content;

}
