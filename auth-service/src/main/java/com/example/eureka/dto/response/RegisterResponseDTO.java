package com.example.eureka.dto.response;


import com.example.eureka.enums.Roles;

public class RegisterResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Roles role;

    // Default constructor
    public RegisterResponseDTO() {}

    // All-args constructor
    public RegisterResponseDTO(Long id, String name, String email, Roles role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters and setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}