package com.elearn.app.elearn_bak.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String save(MultipartFile file, String outputPath, String filename) throws IOException {

        if (filename == null) {

        }
        Path dirPath = Paths.get(outputPath);

        //creates output folder if not exists
        Files.createDirectories(dirPath);

        Path filePath = Paths.get(dirPath.toString(), file.getOriginalFilename());

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();

    }


}
