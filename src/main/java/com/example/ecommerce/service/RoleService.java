package com.example.ecommerce.service;

import com.example.ecommerce.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role newRole(Role role);
    List<Role> getRoles();
    List<Role> addAllDefaultRole();
    Optional<Role> getRole(String authority);
}
