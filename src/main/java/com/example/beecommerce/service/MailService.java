package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.User;
import jakarta.mail.MessagingException;

public interface MailService {
    void sendRegisterVerifyEmail(User user) throws MessagingException;
}
