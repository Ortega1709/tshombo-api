package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.dto.PromotionDto;
import com.ortega.tshomboapi.service.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> getPromotionByStoreId(@PathVariable("storeId") Long storeId) {
        return promotionService.getPromotionByStoreId(storeId);
    }

    @PostMapping("/{storeId}/promotions")
    public ResponseEntity<Object> savePromotion(@PathVariable("storeId") Long storeId, @RequestBody PromotionDto promotionDto) {
        return promotionService.savePromotion(storeId, promotionDto);
    }


    @PutMapping("/{storeId}/promotions")
    public ResponseEntity<Object> updatePromotion(@PathVariable("storeId") Long storeId, @RequestBody PromotionDto promotionDto) {
        return promotionService.updatePromotion(storeId, promotionDto);
    }


    @DeleteMapping("/promotions/{id}")
    public ResponseEntity<Object> deletePromotionById(@PathVariable("id") Long id) {
        return promotionService.deleteByPromotionId(id);
    }
}
