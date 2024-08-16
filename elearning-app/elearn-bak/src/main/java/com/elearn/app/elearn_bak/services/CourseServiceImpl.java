package com.elearn.app.elearn_bak.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearn.app.elearn_bak.dtos.CourseDto;
import com.elearn.app.elearn_bak.entities.Course;
import com.elearn.app.elearn_bak.exception.ResourceNotFoundException;
import com.elearn.app.elearn_bak.repository.CourseRepo;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepo courseRepo;
    
    private ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper){
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto create(CourseDto courseDto) {
        Course savedCourse = dtoToEntity(courseDto);
        return entityToDto(courseRepo.save(savedCourse));
    }


    @Override
    public CourseDto getById(String courseId) {
        Course course = courseRepo
            .findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course with given Id not found"));        
        return entityToDto(course);
    }

    @Override
    public List<CourseDto> getAll(int pageNumber, int pageSize) {
        List<Course> courses = courseRepo.findAll();
        List<CourseDto> courseDtos = courses
                .stream()
                .map(course -> entityToDto(course))
                .collect(Collectors.toList());

        return courseDtos;
    }

    @Override
    public CourseDto update(CourseDto courseDto, String courseId) {
        Course course = courseRepo
            .findById(courseId)
            .orElseThrow( ()-> new ResourceNotFoundException("Course with provided Id not found!"));
        course.setTitle(courseDto.getId());
        course.setShortDesc(courseDto.getShortDesc());
        course.setLongDesc(courseDto.getLongDesc());
        Course savedCourse = courseRepo.save(course);
        return entityToDto(savedCourse);
    }

    @Override
    public void delete(String courseId) {
        
        Course course = courseRepo.
            findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found !"));
        courseRepo.delete(course);
        
    }

    @Override
    public List<CourseDto> searchByTitle(String keyword) {
        List<Course> courses = courseRepo.findByTitle(keyword);
        return courses
            .stream()
            .map(course -> entityToDto(course))
            .toList();            
    }

    //manually converting dto to entity 
    //this is grunt work 
    //to avaoid this grunt work we can use ModelMapper dependency
    public CourseDto entityToDto(Course course){

        CourseDto courseDto = modelMapper.map(course, CourseDto.class);     //automated the mapping process

        // CourseDto courseDto = new CourseDto();
        // courseDto.setTitle(course.getTitle());
        // courseDto.setId(course.getId());
        // courseDto.setShortDesc(course.getShortDesc());
        // courseDto.setLongDesc(course.getLongDesc());
//      do for the rest of the properties

        return courseDto;
    }

    public Course dtoToEntity(CourseDto courseDto){

        Course course = modelMapper.map(courseDto, Course.class);   //automating the mapping process

        // Course course = new Course();
        // course.setId(courseDto.getId());
        // course.setTitle(courseDto.getTitle());
        //do this for the rest of the fields

        return course;
    }

}
