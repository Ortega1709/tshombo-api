package com.ortega.tshomboapi.service.impl;

import com.ortega.tshomboapi.dto.PromotionDto;
import com.ortega.tshomboapi.model.Promotion;
import com.ortega.tshomboapi.model.Store;
import com.ortega.tshomboapi.repository.PromotionRepository;
import com.ortega.tshomboapi.repository.StoreRepository;
import com.ortega.tshomboapi.service.IPromotionService;
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
public class PromotionService implements IPromotionService {

    private final PromotionRepository promotionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Cacheable("promotion")
    public ResponseEntity<Object> getAllPromotions() {
        return ResponseHandler.response("Promotions fetched", HttpStatus.OK, promotionRepository.findAll());
    }

    @Override
    @Cacheable("promotion")
    public ResponseEntity<Object> getPromotionById(UUID id) {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        return promotion.map(value -> ResponseHandler.response("Promotion fetched", HttpStatus.OK, value)).orElseGet(() -> ResponseHandler.response("Promotion not found", HttpStatus.NOT_FOUND, null));
    }

    @Override
    @Cacheable("promotion")
    public ResponseEntity<Object> getPromotionByStoreId(UUID storeId) {
        Optional<Promotion> promotion = promotionRepository.findById(storeId);
        return promotion.map(value -> ResponseHandler.response("Promotion fetched", HttpStatus.OK, value)).orElseGet(() -> ResponseHandler.response("Promotion not found", HttpStatus.NOT_FOUND, null));
    }

    @Override
    @CacheEvict(allEntries = true, value = "promotion")
    public ResponseEntity<Object> savePromotion(UUID storeId, PromotionDto promotionDto) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isPresent()) {

            Promotion promotion = Promotion.builder()
                    .name(promotionDto.getName())
                    .description(promotionDto.getDescription())
                    .endDate(promotionDto.getEndDate())
                    .store(store.get())
                    .build();

            return ResponseHandler.response("Promotion created", HttpStatus.CREATED, promotionRepository.save(promotion));
        }
        return ResponseHandler.response("Store not found", HttpStatus.NOT_FOUND, null);
    }

    @Override
    @CacheEvict(allEntries = true, value = "promotion")
    public ResponseEntity<Object> updatePromotion(UUID storeId, PromotionDto promotionDto) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isPresent()) {

            Promotion promotion = Promotion.builder()
                    .promotionId(UUID.fromString(promotionDto.getPromotionId()))
                    .description(promotionDto.getDescription())
                    .endDate(promotionDto.getEndDate())
                    .store(store.get())
                    .build();

            return ResponseHandler.response("Promotion updated", HttpStatus.OK, promotionRepository.save(promotion));
        }
        return ResponseHandler.response("Store not found", HttpStatus.NOT_FOUND, null);
    }

    @Override
    @CacheEvict(allEntries = true, value = "promotion")
    public ResponseEntity<Object> deleteByPromotionId(UUID id) {
        promotionRepository.deleteById(id);
        return ResponseHandler.response("Promotion deleted", HttpStatus.OK, null);
    }
}
