package abb.tech.auth_service.service;

import abb.tech.auth_service.model.User;
import abb.tech.auth_service.dto.UserResponse;
import java.util.List;

public interface UserService {
    UserResponse findById(Long id);
    List<UserResponse> findAll();
}
