package com.dekankilic.image_upload_db.service;

import com.dekankilic.image_upload_db.model.Image;
import com.dekankilic.image_upload_db.repository.ImageRepository;
import com.dekankilic.image_upload_db.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public String uploadImage(MultipartFile file) {
        Image image = null;
        try {
            image = imageRepository.save(Image.builder()
                            .name(file.getOriginalFilename())
                            .type(file.getContentType())
                            .imageData(ImageUtils.compressImage(file.getBytes())) // I do not want to keep hard coded image information. I want to compress it before I store it into the db.
                            .build()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(image.getId() != null){
            return "file uploaded successfully: " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<Image> optionalImage = imageRepository.findByName(fileName);
        return optionalImage.map(image -> ImageUtils.decompressImage(image.getImageData())).orElse(null);
    }
}
