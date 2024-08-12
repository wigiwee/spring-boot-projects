package com.elearn.app.elearn_bak.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.elearn.app.elearn_bak.dtos.CategoryDto;
import com.elearn.app.elearn_bak.entities.Category;
import com.elearn.app.elearn_bak.exception.ResourceNotFoundException;
import com.elearn.app.elearn_bak.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;

    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper){
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;

    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepo.findAll();
        return categories
            .stream()
            .map(category -> entityToDto(category))
            .toList();
    }

    @Override
    public CategoryDto update(CategoryDto newCategory, String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow( ()-> new ResourceNotFoundException("Category not found"));
        category.setDesc(newCategory.getDesc());
        category.setTitle(newCategory.getTitle());
        Category savedCat = categoryRepo.save(category);
        return entityToDto(savedCat);
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        String catId = UUID.randomUUID().toString();
        categoryDto.setId(catId);
        
        //date
        categoryDto.setAddedDate(new Date());
        Category savedCategory = categoryRepo.save(dtoToEntity(categoryDto));
        return entityToDto(savedCategory);

    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepo
            .findById(categoryId)
            .orElseThrow(()-> new ResourceNotFoundException("Category not found!"));

            categoryRepo.delete(category);
    }


    public CategoryDto findById(String catId) {
        Category category = categoryRepo.findById(catId).orElseThrow( ()-> new ResourceNotFoundException("Category not found"));
        return entityToDto(category);
    }
    @Override
    public List<CategoryDto> searchByTitle(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByTitle'");
    }

    public CategoryDto entityToDto(Category category){
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    public Category dtoToEntity(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }

}
