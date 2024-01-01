package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Image;
import com.example.beecommerce.pojo.requests.ImageRequest;
import com.example.beecommerce.pojo.requests.ImageUpdateRequest;

import java.util.List;

public interface ImageService {
    Image createImage(ImageRequest imageRequest);


    List<Image> listImageAll();

    void deleteImage(Long id);

    Image updateImage(ImageUpdateRequest imageUpdateRequest, Long id);

    List<Image> listImageByProductID(Long id);
}
