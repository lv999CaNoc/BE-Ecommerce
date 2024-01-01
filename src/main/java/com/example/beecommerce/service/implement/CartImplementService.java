package com.example.beecommerce.service.implement;

import com.example.beecommerce.exception.UserNotFoundException;
import com.example.beecommerce.pojo.entity.Cart;
import com.example.beecommerce.pojo.entity.Product;
import com.example.beecommerce.pojo.entity.User;
import com.example.beecommerce.pojo.requests.CartRequest;
import com.example.beecommerce.pojo.responses.CartResponse;
import com.example.beecommerce.pojo.responses.ProductResponse;
import com.example.beecommerce.repository.CartRepository;
import com.example.beecommerce.repository.ProductRepository;
import com.example.beecommerce.repository.UserRepository;
import com.example.beecommerce.service.CartService;
import com.example.beecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
@Transactional
public class CartImplementService implements CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart createCart(CartRequest cartRequest) {
        Product product = productRepository.findProductById(cartRequest.getProductId());
        if (product == null) {
            throw new UserNotFoundException("Product not found for product id = " + cartRequest.getProductId());
        }
        User user = userRepository.findUserById(cartRequest.getUserId());
        if (user == null) {
            throw new UserNotFoundException("User not found for user id = " + cartRequest.getUserId());
        }
        Cart cart = new Cart();
        cart.setDeletedAt(false);
        cart.setUserId(cartRequest.getUserId());
        cart.setProductId(cartRequest.getProductId());
        cart.setQuantity(cartRequest.getQuantity());
        cart.setColor(cartRequest.getColor());
        cart.setSize(cartRequest.getSize());
        cart.setTotal(cartRequest.getTotal());
        cart.setCreatedAt(new Date());

        return cartRepository.save(cart);
    }

    @Override
    public CartResponse getCartById(Long id) {
        Cart cart = cartRepository.getCartById(id);
        if (cart == null) {
            throw new UserNotFoundException("Cart not found for cart id = " + id);
        }
        CartResponse cartResponse = new CartResponse();
        ProductResponse productResponse = productService.findProductById(cart.getProductId());
        cartResponse.setProductResponse(productResponse);
        cartResponse.setCart(cart);
        return cartResponse;
    }

    @Override
    public List<CartResponse> listCartByUserId(Long id) {
        User user = userRepository.findUserById(id);
        List<CartResponse> cartResponseList = new ArrayList<>();
        if (user == null) {
            throw new UserNotFoundException("User not found for user id = " + id);
        }
        List<Cart> carts = cartRepository.getCartsByUserIdAndDeletedAt(id, false);
        for (Cart cart : carts) {
            CartResponse cartResponse = new CartResponse();
            ProductResponse productResponse = productService.findProductById(cart.getProductId());
            cartResponse.setProductResponse(productResponse);
            cartResponse.setCart(cart);
            cartResponseList.add(cartResponse);
        }

        return cartResponseList;
    }

    @Override
    public void deleteCart(Long id) {
        Cart cart = cartRepository.getCartById(id);
        if (cart == null) {
            throw new UserNotFoundException("Cart not found for cart id = " + id);
        }
        cart.setDeletedAt(true);
        cartRepository.save(cart);
    }


}
