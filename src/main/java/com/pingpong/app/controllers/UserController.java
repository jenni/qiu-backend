package com.pingpong.app.controllers;

import com.pingpong.app.entities.User;
import com.pingpong.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService service;

    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll() {
        return service.findAll();
    }
}
