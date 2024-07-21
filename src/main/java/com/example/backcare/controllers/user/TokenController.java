package com.example.backcare.controllers.user;

import com.example.backcare.controllers.dto.LoginRequest;
import com.example.backcare.controllers.dto.LoginResponse;
import com.example.backcare.repositories.user.UserRepository;
import com.example.backcare.services.JWTService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public TokenController(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.email());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("Invalid credentials");
        }

        var userModel = user.get();
        var jwtValue = jwtService.generateToken(userModel.getName(), userModel.getEmail(), userModel.getBirthdate());

        return ResponseEntity.ok(new LoginResponse(jwtValue));
    }
}
