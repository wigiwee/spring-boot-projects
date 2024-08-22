package com.elearn.app.elearn_bak.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elearn.app.elearn_bak.config.AppConstants;
import com.elearn.app.elearn_bak.dtos.CourseDto;
import com.elearn.app.elearn_bak.dtos.CustomMessage;
import com.elearn.app.elearn_bak.dtos.CustomPageResponse;
import com.elearn.app.elearn_bak.services.CourseServiceImpl;
import com.elearn.app.elearn_bak.services.FileService;
import com.elearn.app.elearn_bak.services.FileServiceImpl;

import jakarta.validation.Valid;
import lombok.Value;

import java.io.IOException;

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
    public ResponseEntity<CustomPageResponse<CourseDto>> getAll (
        @RequestParam(value="pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER ) int pageNumber,
        @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE ) int pageSize,
        @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY ) String sortBy, 
        @RequestParam(value = "sortSeq", required = false, defaultValue = AppConstants.DEFAULT_SORT_SEQUENCE ) String sortSeq) {

        System.out.println(sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAll(pageNumber, pageSize, sortBy, sortSeq));
    }

    @GetMapping("{courseId}")
    public CourseDto getCourse (@PathVariable String courseId) {
        return courseService.getById(courseId);
    }
    
    @PostMapping
    public ResponseEntity<CourseDto> createCourse( @Valid @RequestBody CourseDto newCourse) {
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

    //image upload api
    @PostMapping("/{courseId}/banner")
    public ResponseEntity<CourseDto> uploadBanner(
        @PathVariable String courseId,
        @RequestParam("banner") MultipartFile banner) throws IOException {
        
        return ResponseEntity.ok(courseService.saveBanner(banner, courseId));
    }    
}
