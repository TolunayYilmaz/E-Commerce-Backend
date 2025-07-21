package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private final RoleService roleService;

    @PostMapping()
    public Role saveRole(@RequestBody Role role){
       return roleService.newRole(role);
    }
    @PostMapping("/default")
    public List<Role> saveAllRole(){
        return roleService.addAllDefaultRole();
    }
    @GetMapping()
    public List<Role> getAllRoles(){
        return roleService.getRoles();
    }
}
