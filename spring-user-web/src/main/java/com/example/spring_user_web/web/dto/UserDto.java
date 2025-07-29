package com.example.spring_user_web.web.dto;

import com.example.spring_user_web.model.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private long id;
    private String name;
    private String email;
    private int age;
    private String createdAt;

    public UserDto(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        age = user.getAge();
        createdAt = user.getCreatedAt();
    }
}
