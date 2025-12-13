package com.app.usercreation.controller;

import com.app.usercreation.entity.User;
import com.app.usercreation.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home() {
        return "User Creation Application is running";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name) {
        userRepository.save(new User(name));
        return "User created successfully";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
