package com.thiagovm.dev.pizzaria.dto;


import java.util.List;


public record UserRequestDto(String name,
                             String email,
                             String telefone,
                             String password) {

}
