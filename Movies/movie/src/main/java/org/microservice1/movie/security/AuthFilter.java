//package org.microservice1.movie.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class AuthFilter extends OncePerRequestFilter {
//
//    private final String AUTH_URL = "http://localhost:8081/api/v1/test";
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwtToken;
//
//        if(authHeader == null || !authHeader.startsWith("Bearer ")){
//            response.setStatus(403);
//            return;
//        }
//
//        jwtToken = authHeader.substring(7);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(jwtToken);
//        HttpEntity<Void> entity = new HttpEntity<>(headers);
//
//        try {
//            ResponseEntity<Void> authResponse = restTemplate.exchange(AUTH_URL, HttpMethod.GET, entity, Void.class);
//            if(authResponse.getStatusCode().is2xxSuccessful()) {
//                filterChain.doFilter(request, response);
//            }
//        } catch (HttpClientErrorException e) {
//            response.setStatus(403);
//
//
//            return;
//        }
//    }
//}
