package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.service.IStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/storage")
public class StorageController {

    private final IStorageService storageService;

    @PostMapping(path = "/stores/{storeId}/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public CompletableFuture<ResponseEntity<Object>> uploadStore(@PathVariable("storeId") Long storeId, @RequestParam("file") MultipartFile file) throws ExecutionException, InterruptedException {
        return storageService.uploadStoreImage(storeId, file);
    }

    @PostMapping(path = "/promotions/{promotionId}/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public CompletableFuture<ResponseEntity<Object>> uploadPromotion(@PathVariable("promotionId") Long promotionId, @RequestParam("file") MultipartFile file) throws ExecutionException, InterruptedException {
        return storageService.uploadPromotionImage(promotionId, file);
    }

    @PostMapping(path = "/phones/{phoneId}/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public CompletableFuture<ResponseEntity<Object>> uploadPhone(@PathVariable("phoneId") Long storeId, @RequestParam("file") MultipartFile file) throws ExecutionException, InterruptedException {
        return storageService.uploadPhoneImage(storeId, file);
    }

    @PostMapping(path = "test/{phoneId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public CompletableFuture<String> upload(@PathVariable("phoneId") Long phoneId, @RequestParam("file") MultipartFile file) {
        return storageService.upload(file);
    }

    @GetMapping(value = "/images/{link}", produces = { "image/jpeg", "image/png" })
    public Mono<Resource> getImageByLink(@PathVariable("link") String link) {
        return storageService.getImage(link);
    }

}

