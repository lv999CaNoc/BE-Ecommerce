package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Shop;
import com.example.beecommerce.pojo.requests.ShopRequest;

public interface ShopService {

    Shop createShop(ShopRequest request);

    Shop getShopByUserId(Long id);

}
