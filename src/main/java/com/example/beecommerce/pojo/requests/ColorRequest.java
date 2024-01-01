package com.example.beecommerce.pojo.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorRequest {
    @NotNull(message = "Color not null")
    private List<String> colors;
    @NotNull(message = "id_product not null")
    private Long id_product;
}
