package abb.tech.auth_service.service.impl;

import abb.tech.auth_service.model.Permission;
import abb.tech.auth_service.repository.PermissionRepository;
import abb.tech.auth_service.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public Permission findByName(String name) {
        return permissionRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Permission not found: " + name));
    }
}
