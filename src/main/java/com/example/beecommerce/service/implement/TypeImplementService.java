package com.example.beecommerce.service.implement;

import com.example.beecommerce.pojo.entity.Type;
import com.example.beecommerce.pojo.requests.TypeRequest;
import com.example.beecommerce.repository.TypeRepository;
import com.example.beecommerce.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeImplementService implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type saveType(TypeRequest typeRequest) {
        Type type = new Type();
        type.setName(typeRequest.getName());
        return typeRepository.save(type);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }
}
