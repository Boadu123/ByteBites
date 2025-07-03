package com.example.eureka.mapper;

import com.example.eureka.dto.request.RegisterUserRequestDTO;
import com.example.eureka.dto.response.RegisterResponseDTO;
import com.example.eureka.models.UserModels;

public class RegisterMapper {

    public static UserModels toUserModel(RegisterUserRequestDTO registerRequestDTO) {
        if (registerRequestDTO == null) {
            return null;
        }

        return UserModels.builder()
                .name(registerRequestDTO.getName())
                .email(registerRequestDTO.getEmail())
                .password(registerRequestDTO.getPassword())
                .roles(registerRequestDTO.getRole())
                .build();
    }

    public static RegisterResponseDTO toRegisterResponseDTO(UserModels user) {
        if (user == null) {
            return null;
        }

        return new RegisterResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles()
        );
    }
}

