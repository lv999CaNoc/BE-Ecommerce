package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Size;
import com.example.beecommerce.pojo.requests.SizeRequest;

import java.util.List;

public interface SizeService {
    Size createSize(SizeRequest sizeRequest);

    void deleteSize(Long id);

    List<Size> listSizeAll();

    List<Size> listSizeByProductID(Long id);
}
