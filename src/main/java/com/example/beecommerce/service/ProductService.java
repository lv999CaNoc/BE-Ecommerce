package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Product;
import com.example.beecommerce.pojo.requests.ProductRequest;
import com.example.beecommerce.pojo.requests.ProductUpdateRequest;
import com.example.beecommerce.pojo.responses.ProductPageResponse;
import com.example.beecommerce.pojo.responses.ProductResponse;

import java.util.List;

public interface ProductService {


    Product saveProduct(ProductRequest productRequest);

    void deleteProductById(Long id);

    Product updateProduct(ProductUpdateRequest productUpdateRequest, Long id);

    List<ProductResponse> listAll();
    List<ProductResponse> listProductByTypeId(Long typeId);
    List<ProductResponse> listProductByShopId(Long id);

    ProductResponse findProductById(Long id);

    ProductPageResponse getProductByPage(int page, int limit,long id);
}
