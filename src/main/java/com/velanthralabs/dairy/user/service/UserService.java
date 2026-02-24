package com.velanthralabs.dairy.user.service;

import com.velanthralabs.dairy.common.entity.User;
import com.velanthralabs.dairy.common.enums.Role;
import com.velanthralabs.dairy.user.dto.SignupRequest;
import com.velanthralabs.dairy.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerStaff(SignupRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        User staff = User.builder()
                .name(request.name())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ROLE_EDITOR) // Hardcoded to STAFF for safety
                .active(true)
                .build();

        userRepository.save(staff);
    }
}