package com.elearn.app.elearn_bak.services;

import com.elearn.app.elearn_bak.dtos.CategoryDto;
import com.elearn.app.elearn_bak.dtos.CourseDto;
import com.elearn.app.elearn_bak.dtos.CustomPageResponse;
import com.elearn.app.elearn_bak.entities.Category;
import com.elearn.app.elearn_bak.entities.Course;
import com.elearn.app.elearn_bak.exception.ResourceNotFoundException;
import com.elearn.app.elearn_bak.repository.CategoryRepo;
import com.elearn.app.elearn_bak.repository.CourseRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor     //to enable constructor injection
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;

    private CourseRepo courseRepo;

    private ModelMapper modelMapper;

    private CourseServiceImpl courseService;
    // public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper){
    //     this.categoryRepo = categoryRepo;
    //     this.modelMapper = modelMapper;
    // }

    @Override
    public CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortSeq) {
        // List<Category> categories = categoryRepo.findAll();

        if (pageNumber == 0) {

        }
        Sort sort;
        if (sortSeq.equals("descending")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sort);


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
        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setPageSize(pageSize);
        customPageResponse.setLast(categoryPage.isLast());
        return customPageResponse;

    }

    @Override
    public CategoryDto update(CategoryDto newCategory, String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
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
    @Transactional
    public void addCourseToCategory(String catId, String courseId) {
        Category category = categoryRepo
                .findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Category with provided id found"));

        Course course = courseRepo
                .findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course with provided id not found"));

        category.addCourse(course);

        categoryRepo.save(category);    //enabling cascade all will ensure that after saving the category the course will be updated too
        System.out.println("Category relationship updated");

    }

    @Override
    public List<CourseDto> getCoursesOfCategory(String categoryId) {
        Category category = categoryRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category with given id not found"));

        List<Course> courses = category.getCourses();

        return courses
                .stream()
                .map(course -> courseService.entityToDto(course))
                .toList();
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        categoryRepo.delete(category);
    }

    public CategoryDto findById(String catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return entityToDto(category);
    }

    @Override
    public List<CategoryDto> searchByTitle(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByTitle'");
    }

    public CategoryDto entityToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    public Category dtoToEntity(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }

}
