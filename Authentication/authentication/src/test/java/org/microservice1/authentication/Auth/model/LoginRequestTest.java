package org.microservice1.authentication.Auth.model;
import org.junit.jupiter.api.Test;
import org.microservice1.authentication.auth.model.LoginRequest;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void testLoginRequestModel() {
        LoginRequest loginRequest1 = LoginRequest.builder()
                .email("test@example.com")
                .password("testPassword")
                .build();
        LoginRequest loginRequest2 = LoginRequest.builder()
                .email("test@example.com")
                .password("testPassword")
                .build();

        assertEquals("test@example.com", loginRequest1.getEmail());
        assertEquals("testPassword", loginRequest1.getPassword());

        assertEquals(loginRequest1.hashCode(), loginRequest2.hashCode());

        assertEquals(loginRequest1, loginRequest2);



    }
}
