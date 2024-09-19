package com.elearn.app.elearn_bak.services;

import com.elearn.app.elearn_bak.dtos.CourseDto;
import com.elearn.app.elearn_bak.dtos.CustomPageResponse;
import com.elearn.app.elearn_bak.dtos.ResourceContentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseService {


    CourseDto create(CourseDto courseDto);

    CourseDto getById(String courseId);

    CustomPageResponse<CourseDto> getAll(int pageNumber, int pageSize, String sortBy, String sortSeq);

    CourseDto update(CourseDto courseDto, String courseId);

    void delete(String courseId);

    List<CourseDto> searchByTitle(String keyword);

    CourseDto saveBanner(MultipartFile file, String courseId) throws IOException;

    ResourceContentType getCourseBannerById(String courseId);


}