package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Type;
import com.example.beecommerce.pojo.requests.TypeRequest;

import java.util.List;


public interface TypeService {
    Type saveType(TypeRequest typeRequest);
    List<Type> listType();
}
