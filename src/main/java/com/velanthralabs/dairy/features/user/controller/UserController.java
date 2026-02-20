package com.velanthralabs.dairy.features.user.controller;

import com.velanthralabs.dairy.features.user.dto.SignupRequest;
import com.velanthralabs.dairy.features.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-staff")
    @PreAuthorize("hasRole('OWNER')") // Only Haridoss can call this
    public ResponseEntity<String> createStaff(@Valid @RequestBody SignupRequest request) {
        userService.registerStaff(request);
        return ResponseEntity.ok("Staff registered successfully by Owner!");
    }
}