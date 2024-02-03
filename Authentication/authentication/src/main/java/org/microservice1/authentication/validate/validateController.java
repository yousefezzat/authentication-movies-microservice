package org.microservice1.authentication.validate;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class validateController {

    @GetMapping("/validate")
    public ResponseEntity<String> validate() {
        return ResponseEntity.ok("ok");
    }

}
