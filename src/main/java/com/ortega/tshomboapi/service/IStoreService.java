package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.dto.StoreDto;
import com.ortega.tshomboapi.model.Store;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStoreService {

    public ResponseEntity<Object> getAllStores();
    public ResponseEntity<Object> getStoreById(Long id);
    public ResponseEntity<Object> getStoreByUserId(Long id);
    public ResponseEntity<Object> saveStore(Long userId, StoreDto storeDto);
    public ResponseEntity<Object> updateStore(Long userId, StoreDto storeDto);
    public ResponseEntity<Object> deleteStoreById(Long id);

}
