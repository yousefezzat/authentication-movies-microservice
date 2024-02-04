package org.microservice1.authentication.Auth.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.microservice1.authentication.auth.controller.ValidateController;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ValidateControllerTest {

    @InjectMocks
    private ValidateController validateController;

    @Test
    public void testValidate() {
        // Perform validate
        ResponseEntity<String> responseEntity = validateController.validate();

        // Verify
        assertEquals(ResponseEntity.ok("ok"), responseEntity);
    }
}
