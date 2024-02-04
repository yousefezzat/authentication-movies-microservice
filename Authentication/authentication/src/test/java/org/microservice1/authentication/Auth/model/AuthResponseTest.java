package org.microservice1.authentication.Auth.model;
import org.junit.jupiter.api.Test;
import org.microservice1.authentication.auth.model.AuthResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthResponseTest {

    @Test
    void testAuthResponseModel() {
        AuthResponse authResponse1 = AuthResponse.builder()
                .token("testToken")
                .build();
        AuthResponse authResponse2 = AuthResponse.builder().token("testToken").build();
        assertEquals(authResponse1.hashCode(), authResponse2.hashCode());
        assertEquals(authResponse1, authResponse2);

        authResponse1.setToken("newToken");
        assertEquals("newToken", authResponse1.getToken());



    }
}

