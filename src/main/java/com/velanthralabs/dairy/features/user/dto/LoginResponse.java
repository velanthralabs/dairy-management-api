package com.velanthralabs.dairy.features.user.dto;

public record LoginResponse(
        String accessToken,
        String tokenType,
        String username,
        String role
) {
    public LoginResponse(String accessToken, String username, String role) {
        this(accessToken, "Bearer", username, role);
    }
}