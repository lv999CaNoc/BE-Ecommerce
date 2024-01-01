package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository <Address,Long>{
    List<Address> findAll();
    Address findAddressById(Long id);

    List<Address> getAddressesByUserId(Long id);
}
