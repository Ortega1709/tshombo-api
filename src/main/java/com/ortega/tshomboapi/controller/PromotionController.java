package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.dto.PromotionDto;
import com.ortega.tshomboapi.service.IPromotionService;
import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PromotionController {

    private final IPromotionService promotionService;

    @GetMapping("/promotions")
    public ResponseEntity<Object> getAllPromotions() {
        return promotionService.getAllPromotions();
    }

    @GetMapping("/{storeId}/promotions")
    public ResponseEntity<Object> getPromotionByStoreId(@PathVariable("storeId") String storeId) {
        return promotionService.getPromotionByStoreId(UUID.fromString(storeId));
    }

    @PostMapping("/{storeId}/promotions")
    public ResponseEntity<Object> savePromotion(@PathVariable("storeId") String storeId, @RequestBody PromotionDto promotionDto) {
        return promotionService.savePromotion(UUID.fromString(storeId), promotionDto);
    }


    @PutMapping("/{storeId}/promotions")
    public ResponseEntity<Object> updatePromotion(@PathVariable("storeId") String storeId, @RequestBody PromotionDto promotionDto) {
        return promotionService.updatePromotion(UUID.fromString(storeId), promotionDto);
    }


    @DeleteMapping("/promotions/{id}")
    public ResponseEntity<Object> deletePromotionById(@PathVariable("id") String id) {
        return promotionService.deleteByPromotionId(UUID.fromString(id));
    }
}
