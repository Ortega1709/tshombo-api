package com.ortega.tshomboapi.service.impl;

import com.ortega.tshomboapi.dto.StoreDto;
import com.ortega.tshomboapi.model.Location;
import com.ortega.tshomboapi.model.Store;
import com.ortega.tshomboapi.model.User;
import com.ortega.tshomboapi.repository.*;
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

@Service
@RequiredArgsConstructor
public class StoreService implements IStoreService {

    private final StoreRepository storeRepository;
    private final PhoneRepository phoneRepository;
    private final PromotionRepository promotionRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Override
    @Cacheable("store")
    public ResponseEntity<Object> getAllStores() {
        return ResponseHandler.response("Stores fetched", HttpStatus.OK, storeRepository.findAll());
    }

    @Override
    @Cacheable("store")
    public ResponseEntity<Object> getStoreById(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(value -> ResponseHandler.response("Store fetched", HttpStatus.OK, value)).orElseGet(() -> ResponseHandler.response("Store not found", HttpStatus.NOT_FOUND, null));
    }

    @Override
    @Cacheable("store")
    public ResponseEntity<Object> getStoreByUserId(Long id) {
        Optional<Store> store = storeRepository.findStoreByUserId(id);
        return store.map(value -> ResponseHandler.response("Store fetched", HttpStatus.OK, value)).orElseGet(() -> ResponseHandler.response("Store not found", HttpStatus.NOT_FOUND, null));
    }

    @Override
    @CacheEvict(allEntries = true, value = "store")
    public ResponseEntity<Object> saveStore(Long userId, StoreDto storeDto) {
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
    public ResponseEntity<Object> updateStore(Long userId, StoreDto storeDto) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            locationRepository.save(storeDto.getLocation());
            Store store = Store.builder()
                    .storeId(storeDto.getStoreId())
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
    public ResponseEntity<Object> deleteStoreById(Long id) {
        promotionRepository.deletePromotionByStoreId(id);
        phoneRepository.deletePhoneByStoreId(id);
        storeRepository.deleteById(id);
        return ResponseHandler.response("User deleted", HttpStatus.OK, null);
    }
}
