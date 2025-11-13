package com.thiagovm.dev.pizzaria.entity;

import com.thiagovm.dev.pizzaria.dto.LoginRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        private String nome;
        private String email;
        private String senha;
        private String telefone;

        public boolean isLoginCorrect(LoginRequest loginRequest,
                                      PasswordEncoder passwordEncoder){
                return passwordEncoder.matches(loginRequest.password(), this.senha);
        }
}


