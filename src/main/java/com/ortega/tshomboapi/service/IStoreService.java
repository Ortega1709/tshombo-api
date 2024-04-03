package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.dto.StoreDto;
import com.ortega.tshomboapi.model.Store;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStoreService {

    public List<Store> getAllStores();
    public Optional<Store> getStoreById(UUID id);
    public Optional<Store> getStoreByUserId(UUID id);
    public void saveStore(UUID userId, StoreDto storeDto);
    public void updateStore(UUID userId, StoreDto storeDto);
    public void deleteStoreById(UUID id);

}
