package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.model.User;
import com.ortega.tshomboapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    @Cacheable("user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Cacheable("user")
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    @CacheEvict(allEntries = true, value = "user")
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    @CacheEvict(allEntries = true, value = "user")
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
