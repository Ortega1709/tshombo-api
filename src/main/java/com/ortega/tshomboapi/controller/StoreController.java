package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.dto.StoreDto;
import com.ortega.tshomboapi.service.IStoreService;
import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        try {
            return ResponseHandler.response("Store fetch", HttpStatus.OK, storeService.getAllStores());
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<Object> getStoreById(@PathVariable("id") String id) {
        try {
            return ResponseHandler.response("Store fetch", HttpStatus.OK, storeService.getStoreById(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("/{userId}/stores")
    public ResponseEntity<Object> saveStore(@PathVariable("userId") String userId, @RequestBody StoreDto storeDto) {
        try {
            storeService.saveStore(UUID.fromString(userId), storeDto);
            return ResponseHandler.response("Store added", HttpStatus.OK, storeDto);
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping("/stores/{id}")
    public ResponseEntity<Object> deleteStoreById(@PathVariable("id") String id) {
        try {
            storeService.deleteStoreById(UUID.fromString(id));
            return ResponseHandler.response("Store deleted", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
