package com.example.beecommerce.pojo.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotiResponse {
    private HttpStatus status;
    private String message;
}
