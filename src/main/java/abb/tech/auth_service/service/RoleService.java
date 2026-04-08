package abb.tech.auth_service.service;

import abb.tech.auth_service.model.Role;

public interface RoleService {
    Role findByName(String name);
}
