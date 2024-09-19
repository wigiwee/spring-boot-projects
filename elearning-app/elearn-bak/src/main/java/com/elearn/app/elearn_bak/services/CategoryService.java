package com.elearn.app.elearn_bak.services;

import com.elearn.app.elearn_bak.dtos.CategoryDto;
import com.elearn.app.elearn_bak.dtos.CourseDto;
import com.elearn.app.elearn_bak.dtos.CustomPageResponse;

import java.util.List;

public interface CategoryService {

    CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortSeq);

    CategoryDto update(CategoryDto categoryDto, String categoryId);

    CategoryDto create(CategoryDto categoryDto);

    void delete(String categoryId);

    List<CategoryDto> searchByTitle(String keyword);

    public void addCourseToCategory(String catId, String courseId);

    public List<CourseDto> getCoursesOfCategory(String categoryId);


}
