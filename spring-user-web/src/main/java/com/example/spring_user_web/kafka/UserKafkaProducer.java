package com.example.spring_user_web.kafka;

import com.example.spring_user_web.web.dto.UserDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserKafkaProducer {

    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public UserKafkaProducer(KafkaTemplate<String, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserToKafka(String topic, UserDto userDto) {
        kafkaTemplate.send(topic, userDto);
        System.out.println("User sent to kafka " +  userDto);
    }
}
