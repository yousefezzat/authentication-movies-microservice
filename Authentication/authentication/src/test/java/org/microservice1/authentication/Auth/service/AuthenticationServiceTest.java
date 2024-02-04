package org.microservice1.authentication.Auth.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.microservice1.authentication.auth.model.AuthResponse;
import org.microservice1.authentication.auth.model.LoginRequest;
import org.microservice1.authentication.auth.model.RegisterRequest;
import org.microservice1.authentication.auth.service.AuthenticationService;
import org.microservice1.authentication.exception.EmailAlreadyExistsException;
import org.microservice1.authentication.jwt.JwtUtil;
import org.microservice1.authentication.user.entity.Roles;
import org.microservice1.authentication.user.entity.User;
import org.microservice1.authentication.user.repository.UserRepo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepo userRepo;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void testRegister() {
        // Create mock RegisterRequest
        RegisterRequest registerRequest = RegisterRequest.builder()
                .name("testUser")
                .email("test@example.com")
                .password("testPassword")
                .build();

        when(userRepo.existsByEmail(registerRequest.getEmail())).thenReturn(false);

        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");


        when(jwtUtil.generateToken(any())).thenReturn("testToken");

        AuthResponse authResponse = authenticationService.register(registerRequest);

        verify(userRepo, times(1)).existsByEmail(registerRequest.getEmail());
        verify(passwordEncoder, times(1)).encode(registerRequest.getPassword());
        verify(userRepo, times(1)).save(any());
        verify(jwtUtil, times(1)).generateToken(any());

        assertEquals("testToken", authResponse.getToken());
    }

    @Test
    void testRegisterWithEmailAlreadyExists() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .name("testUser")
                .email("test@example.com")
                .password("testPassword")
                .build();

        // Mock userRepo.existsByEmail
        when(userRepo.existsByEmail(registerRequest.getEmail())).thenReturn(true);

        // Perform register and expect EmailAlreadyExistsException
        EmailAlreadyExistsException exception = assertThrows(
                EmailAlreadyExistsException.class,
                () -> authenticationService.register(registerRequest)
        );

        // Verify interactions
        verify(userRepo, times(1)).existsByEmail(registerRequest.getEmail());

        // Assertions
        assertEquals("Email is already registered", exception.getMessage());
    }

    @Test
    void testLogin() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@example.com")
                .password("testPassword")
                .build();

        // Mock authenticationManager.authenticate
        when(authenticationManager.authenticate(any()))
                .thenReturn(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        // Mock userRepo.findByEmail
        User user = User.builder()
                .name("testUser")
                .email("test@example.com")
                .password("encodedPassword")
                .roles(Roles.USER)
                .build();
        when(userRepo.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(user));

        // Mock jwtUtil.generateToken
        when(jwtUtil.generateToken(user)).thenReturn("testToken");

        // Perform login
        AuthResponse authResponse = authenticationService.login(loginRequest);

        // Verify interactions
        verify(authenticationManager, times(1)).authenticate(any());
        verify(userRepo, times(1)).findByEmail(loginRequest.getEmail());
        verify(jwtUtil, times(1)).generateToken(user);

        // Assertions
        assertEquals("testToken", authResponse.getToken());
    }
}
