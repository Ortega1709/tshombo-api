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
    public ResponseEntity<Object> getPromotionById(Long id);
    public ResponseEntity<Object> getPromotionByStoreId(Long id);
    public ResponseEntity<Object> savePromotion(Long storeId, PromotionDto promotionDto);
    public ResponseEntity<Object> updatePromotion(Long userId, PromotionDto promotionDto);
    public ResponseEntity<Object> deleteByPromotionId(Long id);

}
