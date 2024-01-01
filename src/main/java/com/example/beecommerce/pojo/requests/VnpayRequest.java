package com.example.beecommerce.pojo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VnpayRequest {
    private String content_pay;
    private Long amount;
    private Long[] list_id_order;
}
