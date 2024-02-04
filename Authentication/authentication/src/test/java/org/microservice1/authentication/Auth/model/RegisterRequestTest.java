package org.microservice1.authentication.Auth.model;

import org.junit.jupiter.api.Test;
import org.microservice1.authentication.auth.model.RegisterRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterRequestTest {

    @Test
    void testRegisterRequestModel() {
        RegisterRequest registerRequest1 = RegisterRequest.builder()
                .name("TestUser")
                .password("testPassword")
                .email("test@example.com")
                .build();

        RegisterRequest registerRequest2 = RegisterRequest.builder()
                .name("TestUser")
                .password("testPassword")
                .email("test@example.com")
                .build();

        assertEquals("TestUser", registerRequest1.getName());
        assertEquals("testPassword", registerRequest1.getPassword());
        assertEquals("test@example.com", registerRequest1.getEmail());

        assertEquals(registerRequest1.hashCode(), registerRequest2.hashCode());
        assertEquals(registerRequest1, registerRequest2);

    }
}
