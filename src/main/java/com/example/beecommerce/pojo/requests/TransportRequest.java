package com.example.beecommerce.pojo.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportRequest {
    @NotNull(message = "Name not null")
    private String name;
    private Integer price;
}
