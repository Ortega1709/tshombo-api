package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.model.User;
import com.ortega.tshomboapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
