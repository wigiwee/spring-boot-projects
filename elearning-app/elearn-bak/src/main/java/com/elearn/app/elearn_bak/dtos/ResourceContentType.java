package com.elearn.app.elearn_bak.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
@Setter
public class ResourceContentType {

    private Resource resource;

    private String contentType;
}
