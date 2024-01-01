package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size,Long> {
    Size findSizeById(Long id);
    List<Size> findSizesByProductId(Long id);
}
