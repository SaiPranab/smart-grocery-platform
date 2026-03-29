package com.smartgrocery.auth.controller;

import com.smartgrocery.auth.entity.UserCredential;
import com.smartgrocery.auth.repository.UserCredentialRepository;
import com.smartgrocery.auth.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCredential user) {
        // Hash the password before saving to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody UserCredential userRequest) {
        // Find user by username
        UserCredential user = repository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verify password
        if (passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            // Generate and return JWT
            String token = jwtService.generateToken(userRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        jwtService.validateToken(token);
        return ResponseEntity.ok("Token is valid");
    }
}
