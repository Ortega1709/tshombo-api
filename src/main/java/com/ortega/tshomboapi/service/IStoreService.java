package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.dto.StoreDto;
import com.ortega.tshomboapi.model.Store;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStoreService {

    public ResponseEntity<Object> getAllStores();
    public ResponseEntity<Object> getStoreById(UUID id);
    public ResponseEntity<Object> getStoreByUserId(UUID id);
    public ResponseEntity<Object> saveStore(UUID userId, StoreDto storeDto);
    public ResponseEntity<Object> updateStore(UUID userId, StoreDto storeDto);
    public ResponseEntity<Object> deleteStoreById(UUID id);

}
