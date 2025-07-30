package com.example.spring_user_web.service;

import com.example.spring_user_web.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender mailSender;

    @SneakyThrows
    public void sendHello(UserDto user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(user.getEmail());
        message.setSubject("Hello");
        message.setText("Your account has been created");
        mailSender.send(message);
    }

    @SneakyThrows
    public void sendGoodBy(UserDto user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(user.getEmail());
        message.setSubject("Good By");
        message.setText("Your account has been deleted");
        mailSender.send(message);
    }

}
