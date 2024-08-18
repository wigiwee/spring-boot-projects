package com.elearn.app.elearn_bak.services;

import java.util.List;

import com.elearn.app.elearn_bak.dtos.VideoDto;

public interface VideoService {

    List<VideoDto> getAll(int pageNumber, int pageSize, String sortBy, String sortSeq);

    VideoDto getById(String videoId);
    
    VideoDto create(VideoDto videoDto);

    VideoDto update(VideoDto videoDto, String videoId);

    void delete(String videoId);

    List<VideoDto> searchByTitle(String keyword);
    
}
