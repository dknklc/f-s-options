package com.dekankilic.image_upload_filesystem.controller;

import com.dekankilic.image_upload_filesystem.service.FileDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class FileDataController {
    private final FileDataService fileDataService;

    @PostMapping
    public ResponseEntity<String> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileDataService.uploadFile(file));
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileDataService.downloadFile(fileName));
    }
}
