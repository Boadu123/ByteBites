package com.example.eureka.services.interfaces;

import com.example.eureka.dto.request.LoginRequestDTO;
import com.example.eureka.dto.request.RegisterUserRequestDTO;
import com.example.eureka.dto.response.LoginResponseDTO;
import com.example.eureka.dto.response.RegisterResponseDTO;

public interface AuthInterface {
    RegisterResponseDTO registerUser(RegisterUserRequestDTO dto);
    LoginResponseDTO login(LoginRequestDTO dto);
}
