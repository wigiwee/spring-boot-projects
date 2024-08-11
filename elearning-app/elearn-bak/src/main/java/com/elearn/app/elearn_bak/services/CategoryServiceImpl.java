package com.elearn.app.elearn_bak.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearn.app.elearn_bak.dtos.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<CategoryDto> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public CategoryDto create(CategoryDto categorydDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void delete(String categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<CategoryDto> searchByTitle(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByTitle'");
    }

}
