package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.ProductInformation;
import com.example.beecommerce.pojo.requests.ProductInformationRequest;

public interface ProductInformationService {
    ProductInformation createProductInfo(ProductInformationRequest productInformationRequest);

    void deleteProductInfo(Long id);

    ProductInformation updateProductInfo(ProductInformationRequest productInformationRequest, Long id);
}
