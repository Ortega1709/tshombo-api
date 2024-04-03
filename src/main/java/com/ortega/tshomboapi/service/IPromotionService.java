package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.dto.PromotionDto;
import com.ortega.tshomboapi.dto.StoreDto;
import com.ortega.tshomboapi.model.Promotion;
import com.ortega.tshomboapi.model.Store;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPromotionService {

    public ResponseEntity<Object> getAllPromotions();
    public ResponseEntity<Object> getPromotionById(UUID id);
    public ResponseEntity<Object> getPromotionByStoreId(UUID id);
    public ResponseEntity<Object> savePromotion(UUID storeId, PromotionDto promotionDto);
    public ResponseEntity<Object> updatePromotion(UUID userId, PromotionDto promotionDto);
    public ResponseEntity<Object> deleteByPromotionId(UUID id);

}
