package com.example.spring_user_web.repository;

import com.example.spring_user_web.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("jpa")
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Long> {
}
