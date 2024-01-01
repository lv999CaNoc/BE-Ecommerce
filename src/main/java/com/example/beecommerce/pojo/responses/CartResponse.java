package com.example.beecommerce.pojo.responses;


import com.example.beecommerce.pojo.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Cart cart;
    private ProductResponse productResponse;
}
