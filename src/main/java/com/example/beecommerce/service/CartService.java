package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Cart;
import com.example.beecommerce.pojo.requests.CartRequest;
import com.example.beecommerce.pojo.responses.CartResponse;

import java.util.List;

public interface CartService {
    Cart createCart(CartRequest cartRequest);
    CartResponse getCartById(Long id);
    List<CartResponse> listCartByUserId(Long id);
    void deleteCart(Long id);
}
