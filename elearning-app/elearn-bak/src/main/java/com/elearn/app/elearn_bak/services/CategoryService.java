package com.elearn.app.elearn_bak.services;

import java.util.List;

import com.elearn.app.elearn_bak.dtos.CategoryDto;

public interface CategoryService {

    List<CategoryDto> getAll();

    CategoryDto update(CategoryDto categoryDto, String categoryId);

    CategoryDto create(CategoryDto categorydDto);

    void delete(String categoryId);

    List<CategoryDto> searchByTitle(String keyword);



}
