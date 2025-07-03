package com.example.eureka.dto.response;

public class LoginResponseDTO {
    private String token;
    private RegisterResponseDTO registerResponseDTO;

    // Default constructor
    public LoginResponseDTO() {}

    // All-args constructor
    public LoginResponseDTO(String token, RegisterResponseDTO registerResponseDTO) {
        this.token = token;
        this.registerResponseDTO = registerResponseDTO;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisterResponseDTO getUser() {
        return registerResponseDTO;
    }

    public void setUser(RegisterResponseDTO registerResponseDTO) {
        this.registerResponseDTO = registerResponseDTO;
    }
}
