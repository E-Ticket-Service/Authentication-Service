package abb.tech.auth_service.service;

import abb.tech.auth_service.dto.AuthResponse;
import abb.tech.auth_service.dto.LoginRequest;
import abb.tech.auth_service.dto.RegisterRequest;
import abb.tech.auth_service.dto.TokenRefreshRequest;
import abb.tech.auth_service.enums.UserStatus;
import abb.tech.auth_service.model.Role;
import abb.tech.auth_service.model.User;
import abb.tech.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already taken");
        }

        Role userRole = roleService.findByName("USER");

        User user = User.builder()
                .name(request.name())
                .lastname(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .status(UserStatus.ACTIVE)
                .roles(Set.of(userRole))
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(TokenRefreshRequest request) {
        String refreshToken = request.refreshToken();
        String userEmail = jwtService.extractUsername(refreshToken);
        String tokenType = jwtService.extractType(refreshToken);

        if (!"refresh".equals(tokenType)) {
            throw new RuntimeException("Invalid token type");
        }

        if (userEmail != null) {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateAccessToken(user);
                String newRefreshToken = jwtService.generateRefreshToken(user);
                return new AuthResponse(accessToken, newRefreshToken);
            }
        }
        throw new RuntimeException("Invalid refresh token");
    }
}