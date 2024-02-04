package org.microservice1.authentication.Auth.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.microservice1.authentication.auth.controller.AuthController;
import org.microservice1.authentication.auth.model.AuthResponse;
import org.microservice1.authentication.auth.model.LoginRequest;
import org.microservice1.authentication.auth.model.RegisterRequest;
import org.microservice1.authentication.auth.service.AuthenticationService;
import org.microservice1.authentication.exception.EmailAlreadyExistsException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    //Couldn't use MockMvc

    @Test
    public void testRegister() {

        // Creat mock RegisterRequest
        RegisterRequest registerRequest = RegisterRequest.builder()
                .name("testUser")
                .email("test@example.com")
                .password("testPassword")
                .build();

        // Create mock AuthResponse
        AuthResponse authResponse = AuthResponse.builder().token("testToken").build();

        // Mock register service call
        when(authenticationService.register(registerRequest)).thenReturn(authResponse);

        // Perform register
        ResponseEntity<?> responseEntity = authController.register(registerRequest);

        // Verify that the authentication service was called with the correct RegisterRequest
        verify(authenticationService, times(1)).register(registerRequest);
        assertEquals(ResponseEntity.ok(authResponse), responseEntity);
    }

    @Test
    public void testRegisterWithEmailAlreadyExistsException() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .name("testUser")
                .email("test@example.com")
                .password("testPassword")
                .build();

        // Create Mock EmailAlreadyExistsException
        EmailAlreadyExistsException exception = new EmailAlreadyExistsException("Email is already registered");

        // Mock register service to throw EmailAlreadyExistsException
        when(authenticationService.register(registerRequest)).thenThrow(exception);

        // Perform
        ResponseEntity<?> responseEntity = authController.register(registerRequest);

        // Verify that the authentication service was called with the correct RegisterRequest
        verify(authenticationService, times(1)).register(registerRequest);
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage()), responseEntity);
    }

    @Test
    public void testLogin() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@example.com")
                .password("testPassword")
                .build();

        AuthResponse authResponse = AuthResponse.builder().token("testToken").build();

        when(authenticationService.login(loginRequest)).thenReturn(authResponse);

        ResponseEntity<AuthResponse> responseEntity = authController.login(loginRequest);

        verify(authenticationService, times(1)).login(loginRequest);
        assertEquals(ResponseEntity.ok(authResponse), responseEntity);
    }

}
