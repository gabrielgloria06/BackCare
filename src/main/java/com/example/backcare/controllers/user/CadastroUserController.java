package com.example.backcare.controllers.user;

import com.example.backcare.models.UserModel;
import com.example.backcare.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CadastroUserController {
    @Autowired
    private UserRepository userRepository;

    
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/auth/cadastro")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel cadastro) {
        System.out.println("Recebendo solicitação de cadastro: " + cadastro);
        String encryptedPassword = passwordEncoder.encode(cadastro.getPassword());
        cadastro.setPassword(encryptedPassword);
        
        
        UserModel cadastro1 = this.userRepository.save(cadastro);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastro1);
    }
}