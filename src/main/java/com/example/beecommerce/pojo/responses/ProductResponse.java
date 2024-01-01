package com.example.beecommerce.pojo.responses;

import com.example.beecommerce.pojo.entity.Color;
import com.example.beecommerce.pojo.entity.Image;
import com.example.beecommerce.pojo.entity.Product;
import com.example.beecommerce.pojo.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Product product;
    private List<Color> colors;
    private List<Image> images;
    private List<Size> sizes;
}
