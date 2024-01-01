package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color,Long> {
    Color findColorById(Long id);


    List<Color> findColorByProductId(Long id);
}
