package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order getOrderById(Long id);
    List<Order> getOrdersByIdUserAndIdStatus(Long userId,Long status);
    List<Order> getOrdersByIdUser(Long userId);
    List<Order> getOrdersByIdShopAndIdStatus(Long shopId,Long status);
    List<Order> getOrdersByIdStatus(Long status);
    List<Order> getOrdersByIdShop(Long shopId);

}
