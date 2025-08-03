package com.example.spring_user_web.web.controller;

import com.example.spring_user_web.service.UserService;
import com.example.spring_user_web.web.dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/ui/users

@Controller
@RequestMapping("ui/users")
@RequiredArgsConstructor
@Tag(name = "user_methods")
public class UserController {

    private final UserService userService;

    @Operation (
            summary = "Show list of all users"
    )
    @GetMapping
    @CircuitBreaker(name = "allUsers", fallbackMethod = "fallbackMethod")
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.findAll().stream().toList();
        model.addAttribute("users", users);
        return "user/list";
    }



    @Operation(
            summary = "Show the page that help to create user"
    )
    @GetMapping("/add")
    @CircuitBreaker(name = "addUserGet", fallbackMethod = "fallbackMethod")
    public String showAddForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/add-form";
    }

    @Operation(
            summary = "Post operation to add new user"
    )
    @PostMapping("/add")
    @CircuitBreaker(name = "addUserPost", fallbackMethod = "fallbackMethod")
    public String addUser(@ModelAttribute UserDto user) {
        user.setCreatedAt(java.time.LocalDateTime.now().toString());
        userService.create(user);
        return "redirect:/ui/users";
    }

    @Operation(
            summary = "Delete operation to delete user by id"
    )
    @GetMapping("/delete/{id}")
    @CircuitBreaker(name = "deleteUserGet", fallbackMethod = "fallbackMethod")
    public String deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/ui/users";
    }

    @Operation(
            summary = "Show the page that help to update user"
    )
    @GetMapping("/update/{id}")
    @CircuitBreaker(name = "updateUserGet", fallbackMethod = "fallbackMethod")
    public String showUpdateForm(Model model, @PathVariable long id) {
        UserDto userDto = userService.findById(id);
        model.addAttribute("user", userDto);
        return "user/update-form";
    }

    @Operation(
            summary = "Post operation that update user"
    )
    @PostMapping("/update")
    @CircuitBreaker(name = "updateUserPost", fallbackMethod = "fallbackMethod")
    public String updateUser(@ModelAttribute UserDto user) {
        user.setCreatedAt(java.time.LocalDateTime.now().toString());
        userService.update(user.getId(), user);
        return "redirect:/ui/users";
    }

    public String fallbackMethod(Model model, Throwable throwable) {
        return "something went wrong";
    }


}
