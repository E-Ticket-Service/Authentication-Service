package abb.tech.auth_service.dto;

public record RegisterRequest(
        String name,
        String lastname,
        String email,
        String password,
        String phoneNumber
) {}