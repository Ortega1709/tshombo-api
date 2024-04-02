package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.model.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> findUserByEmail(String email);
    Boolean existsUsersByEmail(String email);


}
