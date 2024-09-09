package com.elearn.app.elearn_bak.dtos;

import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceContentType {

    private Resource resource;

    private String contentType;
}
