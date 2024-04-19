package com.ortega.tshomboapi.service.impl;

import com.ortega.tshomboapi.dto.PhoneDto;
import com.ortega.tshomboapi.model.Phone;
import com.ortega.tshomboapi.model.Store;
import com.ortega.tshomboapi.repository.PhoneRepository;
import com.ortega.tshomboapi.repository.StoreRepository;
import com.ortega.tshomboapi.service.IPhoneService;
import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneService implements IPhoneService {

    private final StoreRepository storeRepository;
    private final PhoneRepository phoneRepository;

    @Override
    @Cacheable("phone")
    public ResponseEntity<Object> getAllPhones() {
        return ResponseHandler.response("Phones fetched", HttpStatus.OK, phoneRepository.findAll());
    }

    @Override
    @Cacheable("phone")
    public ResponseEntity<Object> getPhoneById(Long id) {
        Optional<Phone> phone = phoneRepository.findById(id);
        return phone.map(value -> ResponseHandler.response("Phone fetched", HttpStatus.OK, value)).orElseGet(() -> ResponseHandler.response("Phone not found", HttpStatus.NOT_FOUND, null));
    }

    @Override
    @Cacheable("phone")
    public ResponseEntity<Object> getPhoneByStoreId(Long id) {
        List<Phone> phone = phoneRepository.findPhoneByStoreId(id);
        return ResponseHandler.response("Phone fetched", HttpStatus.OK, phone);
    }

    @Override
    @CacheEvict(allEntries = true, value = "phone")
    public ResponseEntity<Object> savePhone(Long storeId, PhoneDto phoneDto) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isPresent()) {

            Phone phone = Phone.builder()
                    .brand(phoneDto.getBrand())
                    .description(phoneDto.getDescription())
                    .price(phoneDto.getPrice())
                    .store(store.get())
                    .build();

            return ResponseHandler.response("Phone created", HttpStatus.CREATED, phoneRepository.save(phone));
        }
        return ResponseHandler.response("Store not found", HttpStatus.NOT_FOUND, null);
    }

    @Override
    @CacheEvict(allEntries = true, value = "phone")
    public ResponseEntity<Object> updatePhone(Long storeId, PhoneDto phoneDto) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isPresent()) {

            Phone phone = Phone.builder()
                    .phoneId(phoneDto.getPhoneId())
                    .brand(phoneDto.getBrand())
                    .image(phoneDto.getImage())
                    .description(phoneDto.getDescription())
                    .price(phoneDto.getPrice())
                    .store(store.get())
                    .build();

            return ResponseHandler.response("Phone updated", HttpStatus.OK, phoneRepository.save(phone));
        }
        return ResponseHandler.response("Store not found", HttpStatus.NOT_FOUND, null);
    }

    @Override
    @CacheEvict(allEntries = true, value = "phone")
    public ResponseEntity<Object> deleteByPhoneId(Long id) {
        phoneRepository.deleteById(id);
        return ResponseHandler.response("Phone deleted", HttpStatus.OK, null);
    }
}
