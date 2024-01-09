package com.example.beecommerce.service.implement;

import com.example.beecommerce.pojo.entity.SecureToken;
import com.example.beecommerce.pojo.entity.User;
import com.example.beecommerce.service.MailService;
import com.example.beecommerce.service.SecureTokenService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final SecureTokenService secureTokenService;

    @Value("${server.base.url}")
    private String BASE_URL;

    @Override
    public void sendRegisterVerifyEmail(User user) throws MessagingException {
        SecureToken secureToken = secureTokenService.createSecureToken(user);

        String toAddress = user.getEmail();
        String subject = "[GlobeBuy] Account Registration Confirmation";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_blank\">VERIFY EMAIL</a></h3>"
                + "Thank you,<br>"
                + "Globe Buy.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getName());
        String verifyURL = BASE_URL +  "api/v1/auth/verify-register?token=" + secureToken.getToken();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);

        mailSender.send(message);
    }

}
