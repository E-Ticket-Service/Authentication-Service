package abb.tech.auth_service.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {}
