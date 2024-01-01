package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Image findImageById(Long id);


    List<Image> findImageByProductId(Long id);
}
