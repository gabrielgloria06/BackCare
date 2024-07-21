package com.example.backcare.models;



import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.backcare.controllers.dto.LoginRequest;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuarios")
public class UserModel {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @NotNull
     @NotBlank
     @Column(nullable = false,unique = true)
     private String name;
     @NotNull
     @NotBlank
     @Column(nullable = false)
     private String password;
     @NotNull
     @NotBlank
     @Column(nullable = false,unique = true)
     private String email;
     @NotNull
     @NotBlank
     @Column(nullable = false)
     private String birthdate;

    public UserModel() {
    }

    public UserModel(String name, String password, String email, String birthdate) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }
}
