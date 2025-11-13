package com.thiagovm.dev.pizzaria.controller;


import com.thiagovm.dev.pizzaria.dto.LoginRequest;
import com.thiagovm.dev.pizzaria.dto.LoginResponse;
import com.thiagovm.dev.pizzaria.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TokenController {


    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest loginRequest){
        var LoginResponse = tokenService.Login(loginRequest);

        return ResponseEntity.ok(LoginResponse);

    }




}

