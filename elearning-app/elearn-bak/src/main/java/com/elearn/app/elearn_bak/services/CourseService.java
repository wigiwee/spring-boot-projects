package com.elearn.app.elearn_bak.services;

import com.elearn.app.elearn_bak.dtos.CourseDto;
import com.elearn.app.elearn_bak.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    
    CourseDto create(CourseDto courseDto);

    List<CourseDto> getAll();

    CourseDto update(CourseDto dto, String courseId);

    void delete(String courseId);

}