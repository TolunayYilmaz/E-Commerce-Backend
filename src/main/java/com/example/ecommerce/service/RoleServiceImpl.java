package com.example.ecommerce.service;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role newRole(Role role) {

        Role newRole = new Role();
        newRole.setAuthority(role.getAuthority());
        newRole.setName(role.getName());
        return roleRepository.save(newRole);
    }



    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> addAllDefaultRole() {
        newRole(new Role("admin","Yönetici"));
        newRole(new Role("store","Mağaza"));
        newRole(new Role("customer","Müşteri"));
        return getRoles();
    }

    @Override
    public Optional<Role> getRole(String authority) {
        return roleRepository.finbyRole(authority);
    }


}
