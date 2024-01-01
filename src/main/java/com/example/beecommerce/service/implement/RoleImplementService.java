package com.example.beecommerce.service.implement;

import com.example.beecommerce.pojo.entity.Role;
import com.example.beecommerce.pojo.requests.RoleRequest;
import com.example.beecommerce.repository.RoleRepository;
import com.example.beecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleImplementService implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getListRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        return roleRepository.save(role);
    }
}
