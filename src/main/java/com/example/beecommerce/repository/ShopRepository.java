package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShopRepository extends JpaRepository<Shop,Long> {

    Shop findShopsByUserId(Long id);
    Shop findShopsById(Long id);
}
