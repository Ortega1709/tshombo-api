package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    public ResponseEntity<Object> getAllUsers();
    public ResponseEntity<Object> getUserById(Long id);
    public ResponseEntity<Object> deleteUserById(Long id);
    public ResponseEntity<Object> updateUser(User user);

}
