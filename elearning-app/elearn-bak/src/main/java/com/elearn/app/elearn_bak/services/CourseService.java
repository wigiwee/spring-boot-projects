package com.elearn.app.elearn_bak.services;

import com.elearn.app.elearn_bak.dtos.CourseDto;

import java.util.List;

public interface CourseService {
    
    
    CourseDto create(CourseDto courseDto);

    CourseDto getById(String courseId);
    
    List<CourseDto> getAll(int pageNumber, int pageSize);

    CourseDto update(CourseDto courseDto, String courseId);

    void delete(String courseId);

    List<CourseDto> searchByTitle(String keyword);

}