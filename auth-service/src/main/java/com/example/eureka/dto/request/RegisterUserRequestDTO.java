package com.example.eureka.dto.request;

import com.example.eureka.enums.Roles;

public class RegisterUserRequestDTO {
    private String name;
    private String email;
    private String password;
    private Roles role;

    // Default constructor
    public RegisterUserRequestDTO() {}

    // All-args constructor
    public RegisterUserRequestDTO(String name, String email, String password, Roles role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
