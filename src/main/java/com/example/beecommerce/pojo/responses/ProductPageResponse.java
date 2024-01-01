package com.example.beecommerce.pojo.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPageResponse {
    private List<ProductResponse> productResponses;
    private Integer total;
    private Long totalElement;
}
