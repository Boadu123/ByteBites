package com.example.eureka.controller;

import com.example.eureka.dto.request.LoginRequestDTO;
import com.example.eureka.dto.request.RegisterUserRequestDTO;
import com.example.eureka.dto.response.LoginResponseDTO;
import com.example.eureka.dto.response.RegisterResponseDTO;
import com.example.eureka.services.AuthService;
import com.example.eureka.utils.SuccessResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequestDTO requestDTO) {
        logger.info("Received user registration request - email={}, role={}", requestDTO.getEmail(), requestDTO.getRole());
        RegisterResponseDTO response = authService.registerUser(requestDTO);
        logger.info("User successfully registered - userId={}, email={}", response.getId(), response.getEmail());
        return SuccessResponseUtil.sucessResponseUtil(HttpStatus.CREATED, response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO requestDTO) {
        logger.info("Received login request - email={}", requestDTO.getEmail());
        LoginResponseDTO response = authService.login(requestDTO);
        logger.info("User successfully authenticated - email={}", requestDTO.getEmail());
        return SuccessResponseUtil.sucessResponseUtil(HttpStatus.OK, response);
    }
}
