package com.dekankilic.image_upload_filesystem.service;

import com.dekankilic.image_upload_filesystem.model.FileData;
import com.dekankilic.image_upload_filesystem.repository.FileDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileDataService {
    private final FileDataRepository repository;

    private static final String FOLDER_PATH = "C:\\Users\\dekan\\OneDrive\\Masaüstü\\FolksDev\\image-upload\\";

    public String uploadFile(MultipartFile file) {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        FileData fileData = repository.save(FileData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filePath(filePath)
                        .build());

        // save the file into the specified location
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (fileData.getId() != null) {
            return "file uploaded successfully: " + filePath;
        }
        return null;
    }

    public byte[] downloadFile(String fileName) {
        Optional<FileData> fileData = repository.findByName(fileName);
        String filePath = null;
        if(fileData.isPresent()){
            filePath = fileData.get().getFilePath(); // get the path of the file stored in database
        }
        try {
            if (filePath != null) {
                return Files.readAllBytes(new File(filePath).toPath()); // convert it into the byte array
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
