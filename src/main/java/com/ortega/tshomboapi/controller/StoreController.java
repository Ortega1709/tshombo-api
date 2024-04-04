package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.dto.StoreDto;
import com.ortega.tshomboapi.service.IStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> getStoreByUserId(@PathVariable("userId") Long userId) {
        return storeService.getStoreByUserId(userId);
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<Object> getStoreById(@PathVariable("id") Long id) {
        return storeService.getStoreById(id);
    }

    @PostMapping("/{userId}/stores")
    public ResponseEntity<Object> saveStore(@PathVariable("userId") Long userId, @RequestBody StoreDto storeDto) {
        return storeService.saveStore(userId, storeDto);
    }

    @PutMapping("/{userId}/stores")
    public ResponseEntity<Object> updateStore(@PathVariable("userId") Long userId, @RequestBody StoreDto storeDto) {
        return storeService.updateStore(userId, storeDto);

    }

    @DeleteMapping("/stores/{id}")
    public ResponseEntity<Object> deleteStoreById(@PathVariable("id") Long id) {
        return storeService.deleteStoreById(id);
    }
}
