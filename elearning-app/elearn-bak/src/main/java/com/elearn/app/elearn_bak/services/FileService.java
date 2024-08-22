package com.elearn.app.elearn_bak.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String save(MultipartFile file, String outputPath, String filename) throws IOException;
}
