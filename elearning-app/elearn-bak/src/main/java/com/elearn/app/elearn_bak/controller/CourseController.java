package com.elearn.app.elearn_bak.controller;

import com.elearn.app.elearn_bak.config.AppConstants;
import com.elearn.app.elearn_bak.dtos.CourseDto;
import com.elearn.app.elearn_bak.dtos.CustomMessage;
import com.elearn.app.elearn_bak.dtos.CustomPageResponse;
import com.elearn.app.elearn_bak.dtos.ResourceContentType;
import com.elearn.app.elearn_bak.services.CourseServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// @CrossOrigin("*")      //allowing all origins
// @CrossOrigin("localhost:3000")      //allowing specific origin
// @CrossOrigin("localhost:3000, localhost:8080")      //allowing multiple origins
// @CrossOrigin(origins = "localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST})      //allowing specific methods
// @CrossOrigin(origins = "localhost:3000", allowedHeaders = {"Authorization", "Content-Type"})      //allowing specific headers
@RestController
@RequestMapping("/api/v1/courses")          //specifying versioning of the api      //endpoint must be plural
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping
    public ResponseEntity<CustomPageResponse<CourseDto>> getAll(
            @RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "sortSeq", required = false, defaultValue = AppConstants.DEFAULT_SORT_SEQUENCE) String sortSeq) {

        System.out.println(sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAll(pageNumber, pageSize, sortBy, sortSeq));
    }

    @ResponseStatus(HttpStatus.OK)    //setting the status code
    @GetMapping("{courseId}")
    public CourseDto getCourse(@PathVariable String courseId) {
        return courseService.getById(courseId);
    }

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto newCourse) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseService.create(newCourse));
    }

    @PutMapping("{courseId}")
    public CourseDto updateCourse(@PathVariable String courseId, CourseDto courseDto) {
        return courseService.update(courseDto, courseId);
    }

    @DeleteMapping("{courseId}")
    public ResponseEntity<CustomMessage> deleteCourse(@PathVariable String courseId) {
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("course deleted");
        customMessage.setSuccess(true);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customMessage);
    }

    //image upload api
    @PostMapping("/{courseId}/banner")
    public ResponseEntity<?> uploadBanner(
            @PathVariable String courseId,
            @RequestParam("banner") MultipartFile banner,
            @RequestHeader("Content-Type") String contentTypes,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) throws IOException {

        //accessing request headers

        // System.out.println(request.getContextPath());
        // System.out.println(request.getPathInfo());
        // Enumeration<String> headerNames = request.getHeaderNames();
        // while(headerNames.hasMoreElements()) {
        //     System.out.println(headerNames.nextElement() +" : " + request.getHeader(headerNames.nextElement()));
        // }

        String contentType = banner.getContentType();
        if (
                banner.isEmpty() ||
                        (!contentType.equals("image/png") &&
                                !contentType.equals("image/jpg") &&
                                !contentType.equals("image/jpeg"))) {

            return ResponseEntity.badRequest().body("provided file is invalid file");
        }
        return ResponseEntity.ok(courseService.saveBanner(banner, courseId));
    }

    @GetMapping("/{courseId}/banner")
    public ResponseEntity<Resource> serverBanner(@PathVariable String courseId) {

        ResourceContentType resourceContentType = courseService.getCourseBannerById(courseId);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(resourceContentType.getContentType()))
                .body(resourceContentType.getResource());
    }
}
