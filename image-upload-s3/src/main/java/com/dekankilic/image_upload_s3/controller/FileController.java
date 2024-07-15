package com.dekankilic.image_upload_s3.controller;

import com.dekankilic.image_upload_s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<String> uploadFileToS3(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(s3Service.uploadFile(file));
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFileFromS3(@PathVariable String fileName) {
        byte[] data = s3Service.downloadFile(fileName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(new ByteArrayResource(data));
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<String> deleteFileFromS3(@PathVariable String fileName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(s3Service.deleteFile(fileName));
    }
}
