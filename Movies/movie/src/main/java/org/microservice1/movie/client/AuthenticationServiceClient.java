package org.microservice1.movie.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication-service", url = "${authentication-service.url}")
@Component
public interface AuthenticationServiceClient {

    @GetMapping("/api/v1/validate")
    ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authorizationHeader);
}
