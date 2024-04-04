package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.model.Role;
import com.ortega.tshomboapi.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final IRoleService roleService;

    @GetMapping
    public ResponseEntity<Object> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public ResponseEntity<Object> saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @PutMapping
    public ResponseEntity<Object> updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable("id") Long id) {
        return roleService.deleteRoleById(id);
    }

}
