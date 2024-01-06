package com.example.beecommerce.service.implement;

import com.example.beecommerce.exception.RoleNotFoundException;
import com.example.beecommerce.exception.UserNotFoundException;
import com.example.beecommerce.pojo.entity.Role;
import com.example.beecommerce.pojo.entity.User;
import com.example.beecommerce.pojo.requests.AccountRegisterRequest;
import com.example.beecommerce.pojo.requests.ResetPasswordRequest;
import com.example.beecommerce.pojo.requests.UserRequest;
import com.example.beecommerce.pojo.requests.UserRequestUpdate;
import com.example.beecommerce.pojo.responses.UserPageResponse;
import com.example.beecommerce.repository.RoleRepository;
import com.example.beecommerce.repository.UserRepository;
import com.example.beecommerce.security.JWTGenerator;
import com.example.beecommerce.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@Transactional
public class UserImplementService implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> listAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User saveUser(UserRequest userDTO) {
        Date date = new Date();
        User user = new User();
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encodedPassword);
        user.setUsername(userDTO.getUsername());
        user.setPhoto(userDTO.getPhoto());
        user.setName(userDTO.getName());
        user.setCreatedAt(date);
        Role role_user = roleRepository.findRoleByName(userDTO.getRole());
        if (role_user == null) {
            throw new RoleNotFoundException("Role not found with id ");
        }
        user.setRole(role_user);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserByJWT(String jwt) throws IOException {
        JWTGenerator jwtGenerator = new JWTGenerator();
        String email = jwtGenerator.getUsernameFromJWT(jwt);
        AtomicReference<User> user = new AtomicReference<>(userRepository.getUserByEmailAndIsActive(email, true));
        return user.get();
    }

    public User updateUser(UserRequestUpdate userRequestUpdate, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    Date date = new Date();
                    user.setEmail(userRequestUpdate.getEmail());
                    user.setUsername(userRequestUpdate.getUsername());
                    user.setName(userRequestUpdate.getName());
                    Role role_user = roleRepository.findRoleByName(userRequestUpdate.getRole());
                    if (role_user == null) {
                        throw new RoleNotFoundException("Role not found with id ");
                    }
                    user.setRole(role_user);
                    user.setUpdatedAt(date);

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }


    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public UserPageResponse getUserByPage(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<User> pageUsers = userRepository.findAll(pageable);
        Integer totalPage = pageUsers.getTotalPages();
        Long totalElement = pageUsers.getTotalElements();
        List<User> users = new ArrayList<>();
        for (User user : pageUsers) {
            users.add(user);
        }
        UserPageResponse userPageResponse = new UserPageResponse(users, totalPage, totalElement);
        return userPageResponse;
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        String encodedPassword = passwordEncoder.encode("123456");
        user.setPassword(encodedPassword);
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        return user;
    }

    @Override
    public User register(AccountRegisterRequest account) {
        User user = new User();
        user.setUsername(account.getUsername());
        user.setEmail(account.getEmail());
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        user.setPassword(encodedPassword);
        user.setName(account.getFullname());
        Role custumer_role = account.getIsSeller() ?
                roleRepository.findRoleByName("seller")
                : roleRepository.findRoleByName("customer");
        user.setRole(custumer_role);
        user.setCreatedAt(new Date());
        userRepository.save(user);
        return user;
    }

}
