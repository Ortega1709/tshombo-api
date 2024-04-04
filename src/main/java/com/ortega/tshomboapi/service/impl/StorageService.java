package com.ortega.tshomboapi.service.impl;

import com.ortega.tshomboapi.model.Phone;
import com.ortega.tshomboapi.model.Promotion;
import com.ortega.tshomboapi.model.Store;
import com.ortega.tshomboapi.repository.PhoneRepository;
import com.ortega.tshomboapi.repository.PromotionRepository;
import com.ortega.tshomboapi.repository.StoreRepository;
import com.ortega.tshomboapi.service.IStorageService;
import com.ortega.tshomboapi.util.ResponseHandler;
import com.ortega.tshomboapi.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class StorageService implements IStorageService {

    private final StoreRepository storeRepository;
    private final PromotionRepository promotionRepository;
    private final PhoneRepository phoneRepository;

    @Async
    @SneakyThrows
    protected CompletableFuture<String> uploadFile(MultipartFile file) {
        Thread.sleep(new Random().nextLong(3000, 7000)); // generate sleep time
        String filePath = UploadUtil.gUniqueFileName(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            file.transferTo(new File(UploadUtil.getResourcePath(), filePath));
            return CompletableFuture.completedFuture(filePath);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(null);
        }
    }

    @Override
    public CompletableFuture<ResponseEntity<Object>> uploadPhoneImage(Long phoneId, MultipartFile file) throws ExecutionException, InterruptedException {
        Optional<Phone> phone = phoneRepository.findById(phoneId);
        if (phone.isPresent()) {
            phoneRepository.updatePhoneImage(uploadFile(file).get(), phoneId);
            return CompletableFuture.completedFuture(ResponseHandler.response("File uploaded", HttpStatus.OK, phone));
        }
        return CompletableFuture.completedFuture(ResponseHandler.response("File not uploaded", HttpStatus.INTERNAL_SERVER_ERROR, null));
    }

    @Override
    public CompletableFuture<ResponseEntity<Object>> uploadPromotionImage(Long promotionId, MultipartFile file) throws ExecutionException, InterruptedException {
        Optional<Promotion> promotion = promotionRepository.findById(promotionId);
        if (promotion.isPresent()) {
            promotionRepository.updatePromotionImage(uploadFile(file).get(), promotionId);
            return CompletableFuture.completedFuture(ResponseHandler.response("File uploaded", HttpStatus.OK, promotion));
        }
        return CompletableFuture.completedFuture(ResponseHandler.response("File not uploaded", HttpStatus.INTERNAL_SERVER_ERROR, null));
    }

    @Override
    public CompletableFuture<ResponseEntity<Object>> uploadStoreImage(Long storeId, MultipartFile file) throws ExecutionException, InterruptedException {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isPresent()) {
            storeRepository.updateStoreImage(uploadFile(file).get(), storeId);
            return CompletableFuture.completedFuture(ResponseHandler.response("File uploaded", HttpStatus.OK, store));
        }
        return CompletableFuture.completedFuture(ResponseHandler.response("File not uploaded", HttpStatus.INTERNAL_SERVER_ERROR, null));
    }
}
