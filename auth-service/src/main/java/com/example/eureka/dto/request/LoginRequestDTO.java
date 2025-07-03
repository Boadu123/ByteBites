package com.example.eureka.dto.request;

public class LoginRequestDTO {
    private String email;
    private String password;

    // Default constructor
    public LoginRequestDTO() {}

    // All-args constructor
    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and setters
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
}
