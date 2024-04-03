package com.ortega.tshomboapi.service.impl;

import com.ortega.tshomboapi.dto.StoreDto;
import com.ortega.tshomboapi.model.Location;
import com.ortega.tshomboapi.model.Store;
import com.ortega.tshomboapi.model.User;
import com.ortega.tshomboapi.repository.LocationRepository;
import com.ortega.tshomboapi.repository.StoreRepository;
import com.ortega.tshomboapi.repository.UserRepository;
import com.ortega.tshomboapi.service.IStoreService;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService implements IStoreService {

    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Override
    @Cacheable("store")
    public ResponseEntity<Object> getAllStores() {
        return ResponseHandler.response("Stores fetched", HttpStatus.OK, storeRepository.findAll());
    }

    @Override
    @Cacheable("store")
    public ResponseEntity<Object> getStoreById(UUID id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(value -> ResponseHandler.response("Store fetched", HttpStatus.OK, value)).orElseGet(() -> ResponseHandler.response("Store not found", HttpStatus.NOT_FOUND, null));
    }

    @Override
    @Cacheable("store")
    public ResponseEntity<Object> getStoreByUserId(UUID id) {
        Optional<Store> store = storeRepository.findStoreByUserId(id);
        return store.map(value -> ResponseHandler.response("Store fetched", HttpStatus.OK, value)).orElseGet(() -> ResponseHandler.response("Store not found", HttpStatus.NOT_FOUND, null));
    }

    @Override
    @CacheEvict(allEntries = true, value = "store")
    public ResponseEntity<Object> saveStore(UUID userId, StoreDto storeDto) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {

            Location location = locationRepository.save(storeDto.getLocation());
            Store store = Store.builder()
                    .name(storeDto.getName())
                    .city(storeDto.getCity())
                    .avenue(storeDto.getAvenue())
                    .commune(storeDto.getCommune())
                    .rccm(storeDto.getRccm())
                    .user(user.get())
                    .location(location)
                    .build();

            return ResponseHandler.response("Store created", HttpStatus.CREATED, storeRepository.save(store));
        }
        return ResponseHandler.response("User not found", HttpStatus.NOT_FOUND, null);
    }

    @Override
    @CacheEvict(allEntries = true, value = "store")
    public ResponseEntity<Object> updateStore(UUID userId, StoreDto storeDto) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            locationRepository.save(storeDto.getLocation());
            Store store = Store.builder()
                    .storeId(UUID.fromString(storeDto.getStoreId()))
                    .name(storeDto.getName())
                    .city(storeDto.getCity())
                    .avenue(storeDto.getAvenue())
                    .commune(storeDto.getCommune())
                    .rccm(storeDto.getRccm())
                    .image(storeDto.getImage())
                    .user(user.get())
                    .location(storeDto.getLocation())
                    .build();

            return ResponseHandler.response("Store updated", HttpStatus.OK, storeRepository.save(store));
        }
        return ResponseHandler.response("User not found", HttpStatus.NOT_FOUND, null);
    }

    @Override
    @CacheEvict(allEntries = true, value = "store")
    public ResponseEntity<Object> deleteStoreById(UUID id) {
        storeRepository.deleteById(id);
        return ResponseHandler.response("User deleted", HttpStatus.OK, null);
    }
}
