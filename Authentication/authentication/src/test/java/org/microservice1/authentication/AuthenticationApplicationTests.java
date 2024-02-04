package org.microservice1.authentication;

import org.junit.jupiter.api.Test;
import org.microservice1.authentication.AuthenticationApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void mainMethodRunsSuccessfully() {
        AuthenticationApplication.main(new String[]{});
    }
}
