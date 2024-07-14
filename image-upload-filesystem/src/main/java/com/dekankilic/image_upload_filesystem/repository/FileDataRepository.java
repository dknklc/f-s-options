package com.dekankilic.image_upload_filesystem.repository;

import com.dekankilic.image_upload_filesystem.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

    Optional<FileData> findByName(String name);
}
