package com.example.beecommerce.pojo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long idAddress;
    private Long idPayment;
    private Long idUser;
    private OrderRequestArr[] order;
}
