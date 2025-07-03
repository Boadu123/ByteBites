package com.example.eureka.services;

import com.example.eureka.dto.request.LoginRequestDTO;
import com.example.eureka.dto.request.RegisterUserRequestDTO;
import com.example.eureka.dto.response.LoginResponseDTO;
import com.example.eureka.dto.response.RegisterResponseDTO;
import com.example.eureka.exceptions.EmailAlreadyExistsException;
import com.example.eureka.mapper.LoginMapper;
import com.example.eureka.mapper.RegisterMapper;
import com.example.eureka.models.UserModels;
import com.example.eureka.repository.UserRepository;
import com.example.eureka.security.JwtService;
import com.example.eureka.services.interfaces.AuthInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public RegisterResponseDTO registerUser(RegisterUserRequestDTO requestDTO) {
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already in use");
        }

        UserModels user = RegisterMapper.toUserModel(requestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return RegisterMapper.toRegisterResponseDTO(user);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getEmail(),
                        requestDTO.getPassword()
                )
        );

        UserModels user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user.getEmail());
        return LoginMapper.toLoginResponseDTO(token, user);
    }
}
