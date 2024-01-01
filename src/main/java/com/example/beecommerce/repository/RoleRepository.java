package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
}
