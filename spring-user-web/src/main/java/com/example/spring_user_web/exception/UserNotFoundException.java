package com.example.spring_user_web.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException() {
  }

  public UserNotFoundException(String message) {
        super(message);
    }
}
