package com.example.eureka.controller;

import com.example.eureka.dto.request.LoginRequestDTO;
import com.example.eureka.dto.request.RegisterUserRequestDTO;
import com.example.eureka.dto.response.LoginResponseDTO;
import com.example.eureka.dto.response.RegisterResponseDTO;
import com.example.eureka.services.AuthService;
import com.example.eureka.utils.SuccessResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequestDTO requestDTO) {
        RegisterResponseDTO response = authService.registerUser(requestDTO);
        return SuccessResponseUtil.sucessResponseUtil(HttpStatus.CREATED, response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO requestDTO) {
        LoginResponseDTO response = authService.login(requestDTO);
        return SuccessResponseUtil.sucessResponseUtil(HttpStatus.OK, response);
    }
}
