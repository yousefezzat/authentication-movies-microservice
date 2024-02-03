package org.microservice1.authentication.jwt;
import org.microservice1.authentication.user.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.microservice1.authentication.user.entity.Roles;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestConfiguration
@SpringBootTest
class JwtUtilTest {

//    @Mock
//    private Key key;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @InjectMocks
    @Spy
    private JwtUtil jwtUtil;
    UserDetails user = User.builder()
            .name("testUser")
            .email("testEmail")
            .password("password")
            .roles(Roles.USER)
            .build();
    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(jwtUtil, "secretKey", "fb83ad2e9c56f1a8c94b1d8e4a07ca93a7d480327a3c6888efae31b5c784c4f6");
        ReflectionTestUtils.setField(jwtUtil, "jwtExpiration", "259200000");

    }
    @Test
    void extractAllClaims_ValidToken_ReturnsClaims() {
        String token = generateValidToken();
        Claims claims = jwtUtil.extractAllClaims(token);
        assertNotNull(claims);
    }

    @Test
    void extractClaim_ValidToken_ReturnsClaim() {
        String token = generateValidToken();
        String subject = jwtUtil.extractUsername(token);
        assertEquals("testEmail", subject);
    }

    @Test
    void generateToken_ValidUser_ReturnsToken() {
        UserDetails userDetails =user ;
        String token = jwtUtil.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    void isTokenValid_ValidTokenAndUserDetails_ReturnsTrue() {
        UserDetails userDetails = user;
        String token = jwtUtil.generateToken(userDetails);
        assertTrue(jwtUtil.isTokenValid(token, userDetails));
    }

    @Test
    void isTokenExpired_ValidToken_ReturnsFalse() {
        String token = generateValidToken();
        assertFalse(jwtUtil.isTokenExpired(token));
    }

    private String generateValidToken() {
        Map<String, Object> claims = new HashMap<>();
            return jwtUtil.generateToken(claims, User.builder()
                    .name("testUser")
                    .email("testEmail")
                    .password("password")
                    .roles(Roles.USER)
                    .build());

    }
}
