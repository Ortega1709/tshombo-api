package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.dto.StoreDto;
import com.ortega.tshomboapi.model.Location;
import com.ortega.tshomboapi.model.Store;
import com.ortega.tshomboapi.model.User;
import com.ortega.tshomboapi.repository.LocationRepository;
import com.ortega.tshomboapi.repository.StoreRepository;
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
public class StoreService implements IStoreService {

    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Override
    @Cacheable("store")
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    @Cacheable("store")
    public Optional<Store> getStoreById(UUID id) {
        return storeRepository.findById(id);
    }

    @Override
    @CacheEvict(allEntries = true, value = "store")
    public void saveStore(UUID storeId, StoreDto storeDto) {
        Optional<User> user = userRepository.findById(storeId);
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

            storeRepository.save(store);
        }
    }

    @Override
    @CacheEvict(allEntries = true, value = "store")
    public void updateStore(StoreDto storeDto) {
        //storeRepository.save(store);
    }

    @Override
    @CacheEvict(allEntries = true, value = "store")
    public void deleteStoreById(UUID id) {
        storeRepository.deleteById(id);
    }
}
