package com.ortega.tshomboapi.service.impl;

import com.ortega.tshomboapi.model.Role;
import com.ortega.tshomboapi.repository.RoleRepository;
import com.ortega.tshomboapi.service.IRoleService;
import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<Object> getAllRoles() {
        return ResponseHandler.response("Roles fetched", HttpStatus.OK, roleRepository.findAll());
    }

    @Override
    public ResponseEntity<Object> saveRole(Role role) {
        return ResponseHandler.response("Role created", HttpStatus.CREATED, roleRepository.save(role));
    }

    @Override
    public ResponseEntity<Object> deleteRoleById(Long id) {
        roleRepository.deleteById(id);
        return ResponseHandler.response("Role deleted", HttpStatus.OK, null);
    }

    @Override
    public ResponseEntity<Object> updateRole(Role role) {
        return ResponseHandler.response("Role updated", HttpStatus.OK, roleRepository.save(role));
    }
}
