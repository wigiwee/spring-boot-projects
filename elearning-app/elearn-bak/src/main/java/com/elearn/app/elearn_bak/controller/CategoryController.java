package com.elearn.app.elearn_bak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearn_bak.dtos.CategoryDto;
import com.elearn.app.elearn_bak.dtos.CustomMessage;
import com.elearn.app.elearn_bak.services.CategoryServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/categories")       //endpoint must be plural
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(categoryService.create(categoryDto));
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAll () {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll());
    }   
    
    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryDto> getMethodName(@PathVariable("categoryID") String catId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(catId));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CustomMessage> delete(@PathVariable String categoryId){
        categoryService.delete(categoryId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Category deleted");
        customMessage.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }
    
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> update( @PathVariable String categoryId, @RequestBody CategoryDto newCategory ){

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(categoryService.update(newCategory, categoryId));
    }
}
