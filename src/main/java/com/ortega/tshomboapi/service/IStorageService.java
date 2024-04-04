package com.ortega.tshomboapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IStorageService {

    public CompletableFuture<ResponseEntity<Object>> uploadPhoneImage(Long phoneId, MultipartFile file) throws ExecutionException, InterruptedException;
    public CompletableFuture<ResponseEntity<Object>> uploadPromotionImage(Long promotionId, MultipartFile file) throws ExecutionException, InterruptedException;
    public CompletableFuture<ResponseEntity<Object>> uploadStoreImage(Long storeId, MultipartFile file) throws ExecutionException, InterruptedException;

}
