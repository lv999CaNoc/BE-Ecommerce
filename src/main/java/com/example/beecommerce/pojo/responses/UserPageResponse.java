package com.example.beecommerce.pojo.responses;

import com.example.beecommerce.pojo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageResponse {
    private List<User> users;
    private Integer total;
    private Long totalElement;
}
