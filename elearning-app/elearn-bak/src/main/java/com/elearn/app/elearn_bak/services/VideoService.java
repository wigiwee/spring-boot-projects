package com.elearn.app.elearn_bak.services;

import com.elearn.app.elearn_bak.dtos.CustomPageResponse;
import com.elearn.app.elearn_bak.dtos.VideoDto;

import java.util.List;

public interface VideoService {

    CustomPageResponse<VideoDto> getAll(int pageNumber, int pageSize, String sortBy, String sortSeq);

    VideoDto getById(String videoId);

    VideoDto create(VideoDto videoDto);

    VideoDto update(VideoDto videoDto, String videoId);

    void delete(String videoId);

    List<VideoDto> searchByTitle(String keyword);

}
