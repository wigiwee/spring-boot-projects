package com.elearn.app.elearn_bak.controller;

import com.elearn.app.elearn_bak.config.AppConstants;
import com.elearn.app.elearn_bak.dtos.CategoryDto;
import com.elearn.app.elearn_bak.dtos.CourseDto;
import com.elearn.app.elearn_bak.dtos.CustomMessage;
import com.elearn.app.elearn_bak.dtos.CustomPageResponse;
import com.elearn.app.elearn_bak.services.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// @CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/categories")       //endpoint must be plural
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDto categoryDto
                                    // BindingResult result
    ) {

        //handling this exception globally in GlobalExceptionHandler class as MethodArgumentNotValidException  
        // if(result.hasErrors()){
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");
        // }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.create(categoryDto));
    }

    @GetMapping
    public ResponseEntity<CustomPageResponse<CategoryDto>> getAll(
            @RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "sortSeq", required = true, defaultValue = AppConstants.DEFAULT_SORT_SEQUENCE) String sortSeq) {

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll(pageNumber, pageSize, sortBy, sortSeq));
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryDto> getMethodName(@PathVariable("categoryID") String catId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(catId));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CustomMessage> delete(@PathVariable String categoryId) {
        categoryService.delete(categoryId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Category deleted");
        customMessage.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> update(@PathVariable String categoryId, @RequestBody CategoryDto newCategory) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.update(newCategory, categoryId));
    }

    @GetMapping("/{categoryId}/courses/{courseId}")
    public ResponseEntity<CustomMessage> addCourseToCategory(
            @PathVariable String categoryId,
            @PathVariable String courseId) {

        categoryService.addCourseToCategory(categoryId, courseId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("category Updated ! ");
        customMessage.setSuccess(true);

        return ResponseEntity.ok().body(customMessage);
    }

    @GetMapping("/{categoryId}/courses")
    public ResponseEntity<List<CourseDto>> getCoursesOfCategory(@PathVariable String categoryId) {
        return ResponseEntity.ok().body(categoryService.getCoursesOfCategory(categoryId));
    }


}
