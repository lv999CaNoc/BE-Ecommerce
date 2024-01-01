package com.example.beecommerce.pojo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private String address;

    private String phone;

    private String name_receiver;
    private Long userId;

}
