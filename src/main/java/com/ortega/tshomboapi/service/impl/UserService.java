package com.ortega.tshomboapi.service.impl;

import com.ortega.tshomboapi.model.Store;
import com.ortega.tshomboapi.model.User;
import com.ortega.tshomboapi.repository.StoreRepository;
import com.ortega.tshomboapi.repository.UserRepository;
import com.ortega.tshomboapi.service.IStoreService;
import com.ortega.tshomboapi.service.IUserService;
import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final IStoreService iStoreService;

    @Override
    @Cacheable("user")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseHandler.response("Users fetched", HttpStatus.OK, userRepository.findAll());
    }

    @Override
    @Cacheable("user")
    public ResponseEntity<Object> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> ResponseHandler.response("User fetched", HttpStatus.OK, value)).orElseGet(() -> ResponseHandler.response("User not found", HttpStatus.NOT_FOUND, null));
    }

    @Override
    @CacheEvict(allEntries = true, value = "user")
    public ResponseEntity<Object> deleteUserById(Long id) {
        Optional<Store> store = storeRepository.findStoreByUserId(id);
        store.ifPresent(value -> iStoreService.deleteStoreById(value.getStoreId()));

        userRepository.deleteById(id);
        return ResponseHandler.response("User deleted", HttpStatus.OK, null);

    }

    @Override
    @CacheEvict(allEntries = true, value = "user")
    public ResponseEntity<Object> updateUser(User user) {
        return ResponseHandler.response("User updated", HttpStatus.OK, userRepository.save(user));
    }
}
