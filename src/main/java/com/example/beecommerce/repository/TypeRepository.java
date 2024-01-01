package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findTypeById(Long id);
}
