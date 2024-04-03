package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    public List<User> getAllUsers();
    public Optional<User> getUserById(UUID id);
    public void deleteUserById(UUID id);
    public void updateUser(User user);

}
