package com.smartgrocery.user.controller;

import com.smartgrocery.user.entity.UserProfile;
import com.smartgrocery.user.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserProfileRepository repository;

    @PostMapping("/profile")
    public ResponseEntity<UserProfile> createOrUpdateProfile(@RequestBody UserProfile profile) {
        // In a production system, you would extract the 'username' from the JWT token here
        // to ensure a user can only update their own profile.
        Optional<UserProfile> existingProfile = repository.findByUsername(profile.getUsername());

        if (existingProfile.isPresent()) {
            UserProfile updated = existingProfile.get();
            updated.setFullName(profile.getFullName());
            updated.setAddress(profile.getAddress());
            updated.setPhoneNumber(profile.getPhoneNumber());
            return ResponseEntity.ok(repository.save(updated));
        }

        return ResponseEntity.ok(repository.save(profile));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserProfile> getProfile(@PathVariable String username) {
        return repository.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
