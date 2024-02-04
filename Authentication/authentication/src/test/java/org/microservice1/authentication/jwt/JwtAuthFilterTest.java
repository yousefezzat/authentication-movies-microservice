package org.microservice1.authentication.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtAuthFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    @Test
    public void testDoFilterInternal() throws ServletException, IOException {
        // Create Mock HttpServletRequest, HttpServletResponse, FilterChain
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        // Create Mock token and UserDetails
        String token = "testToken";
        UserDetails userDetails = mock(UserDetails.class);

        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer " + token);
        when(jwtUtil.extractUsername(token)).thenReturn("testUser");
        when(userDetailsService.loadUserByUsername("testUser")).thenReturn(userDetails);
        when(jwtUtil.isTokenValid(token, userDetails)).thenReturn(true);

        // Perform filter
        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        // Verify authentication is set in SecurityContextHolder
        verify(filterChain, times(1)).doFilter(request, response);
        verify(userDetailsService, times(1)).loadUserByUsername("testUser");
    }
}
