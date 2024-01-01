package com.example.beecommerce.controller;

import com.example.beecommerce.pojo.entity.Role;
import com.example.beecommerce.pojo.requests.RoleRequest;
import com.example.beecommerce.pojo.responses.ObjectResponse;
import com.example.beecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    ResponseEntity<ObjectResponse> getListRole() {
        List<Role> roles = roleService.getListRoles();
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.OK, "Query list role successfully", roles)
        );
    }

    @PostMapping("/save")
    ResponseEntity<ObjectResponse> createRole(@RequestBody RoleRequest request) {
        Role role = roleService.createRole(request);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.OK, "Create role successfully", role)
        );
    }
}
