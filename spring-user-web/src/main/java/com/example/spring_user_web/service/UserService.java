package com.example.spring_user_web.service;

import com.example.spring_user_web.exception.UserNotFoundException;
import com.example.spring_user_web.kafka.UserKafkaProducer;
import com.example.spring_user_web.model.User;
import com.example.spring_user_web.repository.UserRepository;
import com.example.spring_user_web.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import java.util.Collection;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserKafkaProducer userKafkaProducer;

    //@Autowired
    //private MailSenderService mailSenderService;

    public Collection<UserDto> findAll() {

        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto findById(long id) {

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return new UserDto(user);
    }

    public UserDto create(UserDto user) {
        userRepository.save(new User(user.getName(), user.getEmail(), user.getAge(), user.getCreatedAt()));
        userKafkaProducer.sendUserToKafka("create", user);
        //mailSenderService.sendHello(user);
        return user;
    }

    public UserDto update(long userId, UserDto userForUpdate) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (userForUpdate.getName() != null && !user.getName().equals(userForUpdate.getName())) {
            user.setName(userForUpdate.getName());
        }

        if (userForUpdate.getEmail() != null && !user.getEmail().equals(userForUpdate.getEmail())) {
            user.setEmail(userForUpdate.getEmail());
        }

        if (user.getAge() != userForUpdate.getAge()) {
            user.setAge(userForUpdate.getAge());
        }
        userRepository.save(user);
        user = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);

        return new UserDto(user);
    }

    public void deleteById(long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            userKafkaProducer.sendUserToKafka("delete", new UserDto(user.get()));
           // mailSenderService.sendGoodBy(new UserDto(user.get()));
        }

    }
}
