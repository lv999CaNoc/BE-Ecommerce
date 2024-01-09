package com.example.beecommerce.controller;

import com.example.beecommerce.pojo.entity.User;
import com.example.beecommerce.pojo.requests.AccountRegisterRequest;
import com.example.beecommerce.pojo.requests.LoginRequest;
import com.example.beecommerce.pojo.requests.ResetPasswordRequest;
import com.example.beecommerce.pojo.responses.JWTResponse;
import com.example.beecommerce.pojo.responses.NotiResponse;
import com.example.beecommerce.pojo.responses.ObjectResponse;
import com.example.beecommerce.security.JWTGenerator;
import com.example.beecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(maxAge = 3600)
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ObjectResponse> login(@RequestBody @Valid LoginRequest loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Login successfully", new JWTResponse(token))
        );
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<NotiResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest, @RequestParam("id") Long id) {
        userService.resetPassword(resetPasswordRequest, id);
        return ResponseEntity.ok().body(
                new NotiResponse(HttpStatus.OK, "Reset password successfully")
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ObjectResponse> register(@RequestBody AccountRegisterRequest account) {
        User user = userService.register(account);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.OK, "Register user successfully", user)
        );
    }

    @GetMapping("/verify-register")
    public ResponseEntity<?> verifyRegister(@RequestParam String token) {
        if (userService.verifyRegister(token)) {
            return ResponseEntity.ok("Verify email successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verify email failed: Invalid token");
    }
}
