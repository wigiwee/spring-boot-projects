package com.elearn.app.elearn_bak.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearn_bak.dtos.CourseDto;
import com.elearn.app.elearn_bak.dtos.CustomMessage;
import com.elearn.app.elearn_bak.services.CourseServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/courses")          //specifying versioning of the api      //endpoint must be plural
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping
    public List<CourseDto> getAll () {
        return courseService.getAll();
    }

    @GetMapping("{courseId}")
    public CourseDto getCourse (@PathVariable String courseId) {
        return courseService.getById(courseId);
    }
    
    @PostMapping("{courseId}")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto newCourse) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(courseService.create(newCourse));
    }

    @PutMapping("{courseId}")
    public CourseDto updateCourse (@PathVariable String courseId, CourseDto courseDto) {
        return courseService.update(courseDto, courseId);
    }

    @DeleteMapping("{courseId}")
    public ResponseEntity<CustomMessage> deleteCourse(@PathVariable String courseId){
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("course deleted");
        customMessage.setSuccess(true);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customMessage);
    }
}
