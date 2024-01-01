package com.example.beecommerce.pojo.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;
}
