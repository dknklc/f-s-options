package com.dekankilic.image_upload_db.controller;

import com.dekankilic.image_upload_db.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(imageService.uploadImage(file));
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable  String fileName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageService.downloadImage(fileName));
    }
}
