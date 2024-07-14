package com.dekankilic.image_upload_db.repository;

import com.dekankilic.image_upload_db.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByName(String name);
}
