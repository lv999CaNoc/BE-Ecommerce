package com.example.beecommerce.pojo.responses;


import com.example.beecommerce.pojo.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderReponse {
    private CartResponse cart;
    private Address address;
    private Payment payment;
    private Status status;
    private Transport transport;
    private String message;
    private User user;
    private Long orderId;

}
