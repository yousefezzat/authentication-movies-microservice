package org.microservice1.authentication.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ValidateController {

    @GetMapping("/validate")
    public ResponseEntity<String> validate() {
        return ResponseEntity.ok("ok");
    }

}
