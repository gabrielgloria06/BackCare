package com.example.backcare.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.backcare.controllers.dto.ChangePasswordRequest;
import com.example.backcare.repositories.user.UserRepository;

import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ChangePasswordController {

    private UserRepository userRepository;
    private PasswordEncoder PasswordEncoder;

    
    
    public ChangePasswordController(UserRepository userRepository,
        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        PasswordEncoder = passwordEncoder;
    }



    @PutMapping("/auth/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        var userOptional = userRepository.findByEmail(changePasswordRequest.email());
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var user = userOptional.get();

        String encryptedPassword = PasswordEncoder.encode(changePasswordRequest.newPassword());

        user.setPassword(encryptedPassword);
        userRepository.save(user);

        return ResponseEntity.ok().build();

    }
}
