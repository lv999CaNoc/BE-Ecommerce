package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Role;
import com.example.beecommerce.pojo.requests.RoleRequest;

import java.util.List;

public interface RoleService {
    List<Role> getListRoles();
    Role createRole(RoleRequest request);
}
