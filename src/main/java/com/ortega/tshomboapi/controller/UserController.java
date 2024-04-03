package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.model.User;
import com.ortega.tshomboapi.service.IUserService;
import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        try {
            return ResponseHandler.response("User fetch", HttpStatus.OK, userService.getAllUsers());
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") String id) {
        try {
            return ResponseHandler.response("User fetch", HttpStatus.OK, userService.getUserById(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return ResponseHandler.response("User updated", HttpStatus.OK, user);
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") String id) {
        try {
            userService.deleteUserById(UUID.fromString(id));
            return ResponseHandler.response("User deleted", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}

