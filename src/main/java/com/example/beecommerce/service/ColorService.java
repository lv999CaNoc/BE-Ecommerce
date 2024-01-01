package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Color;
import com.example.beecommerce.pojo.requests.ColorRequest;

import java.util.List;

public interface ColorService {
    Color createColor(ColorRequest colorRequest);


    List<Color> listColorAll();

    void deleteColor(Long id);

    List<Color> listColorByProductID(Long id);
}
