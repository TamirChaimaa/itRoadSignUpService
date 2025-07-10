package com.ITRoad.signup_service.controllers;

import com.ITRoad.signup_service.dto.UserRegistrationDto;
import com.ITRoad.signup_service.dto.UserResponseDto;
import com.ITRoad.signup_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class SignupController {

    @Autowired
    private com.ITRoad.signup_service.services.UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        try {
            UserResponseDto response = userService.registerUser(registrationDto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }

    }
    @GetMapping("/")
    public String home() {
        return "Welcome to the API!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    // Classe interne pour les erreurs
    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
