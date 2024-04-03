package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.dto.StoreDto;
import com.ortega.tshomboapi.service.IStoreService;
import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StoreController {

    private final IStoreService storeService;

    @GetMapping("/stores")
    public ResponseEntity<Object> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{userId}/stores")
    public ResponseEntity<Object> getStoreByUserId(@PathVariable("userId") String userId) {
        return storeService.getStoreByUserId(UUID.fromString(userId));
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<Object> getStoreById(@PathVariable("id") String id) {
        return storeService.getStoreById(UUID.fromString(id));
    }

    @PostMapping("/{userId}/stores")
    public ResponseEntity<Object> saveStore(@PathVariable("userId") String userId, @RequestBody StoreDto storeDto) {
        return storeService.saveStore(UUID.fromString(userId), storeDto);
    }

    @PutMapping("/{userId}/stores")
    public ResponseEntity<Object> updateStore(@PathVariable("userId") String userId, @RequestBody StoreDto storeDto) {
        return storeService.updateStore(UUID.fromString(userId), storeDto);

    }

    @DeleteMapping("/stores/{id}")
    public ResponseEntity<Object> deleteStoreById(@PathVariable("id") String id) {
        return storeService.deleteStoreById(UUID.fromString(id));
    }
}
