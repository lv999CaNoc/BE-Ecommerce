package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.ProductInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ProductInformationRepository extends JpaRepository<ProductInformation,Long> {
    ProductInformation findProductInformationById(Long id);
    void deleteProductInformationById(Long id);
}
