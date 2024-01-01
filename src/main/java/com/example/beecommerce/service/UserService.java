package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.User;
import com.example.beecommerce.pojo.requests.AccountRegisterRequest;
import com.example.beecommerce.pojo.requests.ResetPasswordRequest;
import com.example.beecommerce.pojo.requests.UserRequest;
import com.example.beecommerce.pojo.requests.UserRequestUpdate;
import com.example.beecommerce.pojo.responses.UserPageResponse;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> listAll();
    User saveUser(UserRequest userDTO) throws IOException;
    User getUserByJWT(String jwt) throws IOException;

    User updateUser(UserRequestUpdate userRequestUpdate, Long id);

    void deleteUserById(Long id);
    UserPageResponse getUserByPage(Integer limit,Integer page);

    void resetPassword(ResetPasswordRequest resetPasswordRequest, Long id);

    User getUserById(Long id);

    User register(AccountRegisterRequest account);
}
