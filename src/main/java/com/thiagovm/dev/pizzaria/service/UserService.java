package com.thiagovm.dev.pizzaria.service;

import com.thiagovm.dev.pizzaria.dto.UserRequestDto;
import com.thiagovm.dev.pizzaria.entity.User;
import com.thiagovm.dev.pizzaria.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService {


    UserRepository userReposiory;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userReposiory, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userReposiory = userReposiory;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createUser(UserRequestDto dto){

        User user= new User();
        user.setNome(dto.name());
        user.setEmail(dto.email());
        user.setTelefone(dto.telefone());
        user.setSenha(bCryptPasswordEncoder.encode(dto.password()));
        userReposiory.save(user);

    }
    public Optional<User>findByEmail(String email){
        return userReposiory.findByEmail(email);
        
    }

    public User findById(UUID token) {
        return userReposiory.findById(token).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
