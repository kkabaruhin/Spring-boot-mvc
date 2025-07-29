package com.example.spring_user_web.repository;

import com.example.spring_user_web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    public Optional<User> findById(long id);

    public List<User> findAll();

    public User save(User user);

    public void deleteById(long id);
}
