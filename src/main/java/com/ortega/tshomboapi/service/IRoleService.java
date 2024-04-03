package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.model.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IRoleService {

    public ResponseEntity<Object> getAllRoles();
    public ResponseEntity<Object> saveRole(Role role);
    public ResponseEntity<Object> deleteRoleById(UUID id);
    public ResponseEntity<Object> updateRole(Role role);
}
