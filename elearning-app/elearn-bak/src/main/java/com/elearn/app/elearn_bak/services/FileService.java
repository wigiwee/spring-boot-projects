package com.elearn.app.elearn_bak.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String save(MultipartFile file, String outputPath, String filename) throws IOException;
}
