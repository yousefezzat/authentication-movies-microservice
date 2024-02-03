package org.microservice1.authentication.auth.service;

import lombok.RequiredArgsConstructor;
import org.microservice1.authentication.auth.model.AuthResponse;
import org.microservice1.authentication.auth.model.LoginRequest;
import org.microservice1.authentication.auth.model.RegisterRequest;
import org.microservice1.authentication.exception.EmailAlreadyExistsException;
import org.microservice1.authentication.jwt.JwtUtil;
import org.microservice1.authentication.user.entity.Roles;
import org.microservice1.authentication.user.entity.User;
import org.microservice1.authentication.user.repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    public AuthResponse register(RegisterRequest request) {
        // Check if the email already exists
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Roles.USER)
                .build();


        userRepo.save(user);
        System.out.println("User registered successfully: " + user.getEmail());


        var jwtToken = jwtUtil.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtUtil.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}