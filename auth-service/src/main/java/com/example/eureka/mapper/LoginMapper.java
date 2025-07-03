package com.example.eureka.mapper;

import com.example.eureka.dto.request.LoginRequestDTO;
import com.example.eureka.dto.response.LoginResponseDTO;
import com.example.eureka.dto.response.RegisterResponseDTO;
import com.example.eureka.models.UserModels;


public class LoginMapper {

    public static UserModels toUserModel(LoginRequestDTO loginRequestDTO) {
        if (loginRequestDTO == null) {
            return null;
        }

        return UserModels.builder()
                .email(loginRequestDTO.getEmail())
                .password(loginRequestDTO.getPassword())
                .build();
    }

    public static LoginResponseDTO toLoginResponseDTO(String token, UserModels user) {
        if (user == null) {
            return null;
        }

        RegisterResponseDTO userResponse = new RegisterResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles()
        );

        return new LoginResponseDTO(token, userResponse);
    }
}