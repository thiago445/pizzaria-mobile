package com.thiagovm.dev.pizzaria.controller;

import com.thiagovm.dev.pizzaria.dto.UserRequestDto;
import com.thiagovm.dev.pizzaria.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



@RestController
public class userController {

   private UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<Void> CreateUser(@RequestBody UserRequestDto dto) {
        var user = userService.findByEmail(dto.email());
        if (user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this existing CPF");
        }
        userService.createUser(dto);
        return ResponseEntity.ok().build();

    }


}
