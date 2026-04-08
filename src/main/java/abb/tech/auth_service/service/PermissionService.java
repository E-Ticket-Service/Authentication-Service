package abb.tech.auth_service.service;

import abb.tech.auth_service.model.Permission;

public interface PermissionService {
    Permission findByName(String name);
}
