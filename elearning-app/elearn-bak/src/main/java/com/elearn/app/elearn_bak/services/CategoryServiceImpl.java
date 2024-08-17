package com.elearn.app.elearn_bak.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.elearn.app.elearn_bak.dtos.CategoryDto;
import com.elearn.app.elearn_bak.dtos.CustomPageResponse;
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
    public CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortSeq) {
        // List<Category> categories = categoryRepo.findAll();

        if(pageNumber ==0){
            
        }
        pageNumber = pageNumber -1;     //by default pageNumber starts from 0 we want it start from 1
        Sort sort;
        if( sortSeq.equals( "descending" )){
            sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);


        Page<Category> categoryPage = categoryRepo.findAll(pageRequest);
        List<Category> categories = categoryPage.getContent();

        List<CategoryDto> categoryDto = categories
            .stream()
            .map(category -> entityToDto(category))
            .toList();
        
        CustomPageResponse<CategoryDto> customPageResponse = new CustomPageResponse<>();
        customPageResponse.setContent(categoryDto);
        customPageResponse.setTotalElements(categoryPage.getTotalElements());
        customPageResponse.setTotalPages(categoryPage.getTotalPages());
        customPageResponse.setPageNumber(pageNumber +1);
        customPageResponse.setPageSize(pageSize);
        customPageResponse.setLast(categoryPage.isLast());
        return customPageResponse;

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
