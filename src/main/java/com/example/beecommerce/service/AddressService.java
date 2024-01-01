package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Address;
import com.example.beecommerce.pojo.requests.AddressRequest;

import java.io.IOException;
import java.util.List;

public interface AddressService {
    List<Address> listAll();
    Address saveAddress(AddressRequest addressRequest) throws IOException;

    Address updateAddress(AddressRequest addressRequest) throws IOException;

    Address findAddressById(AddressRequest addressRequest) throws IOException;
    void deleteAddress(Long id);

    List<Address> findAddressByUserId(Long uid);
}
