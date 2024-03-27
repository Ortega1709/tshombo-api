package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.model.Role;
import com.ortega.tshomboapi.service.IRoleService;
import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final IRoleService roleService;

    @GetMapping
    public ResponseEntity<Object> getAllRoles() {
        try {
            return ResponseHandler.response("success", HttpStatus.OK, roleService.getAllRoles());
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveRole(@RequestBody Role role) {
        try {
            return ResponseHandler.response("success", HttpStatus.OK, roleService.saveRole(role));
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateRole(@RequestBody Role role) {
        try {
            return ResponseHandler.response("success", HttpStatus.OK, roleService.updateRole(role));
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable("id") String id) {
        try {
            roleService.deleteRoleById(UUID.fromString(id));
            return ResponseHandler.response("success", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

}
