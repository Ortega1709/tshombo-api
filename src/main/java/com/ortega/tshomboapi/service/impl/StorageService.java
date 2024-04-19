package com.ortega.tshomboapi.service.impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService implements IStorageService {

    private final StoreRepository storeRepository;
    private final PromotionRepository promotionRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    @Qualifier("webApplicationContext")
    private ResourceLoader resourceLoader;

    @Value("${tshombo.bucket.name}")
    private String BUCKET_NAME;

    private static final String IMAGE_FORMAT = "classpath:images/%s";

    @Async
    @SneakyThrows
    public CompletableFuture<String> upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            File file = UploadUtil.convertToFile(multipartFile, fileName);
            String URL = this.uploadFile(file, fileName);

            file.deleteOnExit();
            log.info(URL);
            return CompletableFuture.completedFuture(URL);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CompletableFuture.completedFuture(null);
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName); // Replace with your bucker name
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        InputStream inputStream = StorageService.class.getClassLoader().getResourceAsStream("tshombo-storage-firebase-adminsdk-nl9pu-55cae7ffd3.json"); // change the file name with your one
        assert inputStream != null;
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media";
        return String.format(DOWNLOAD_URL, BUCKET_NAME, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    @Override
    public CompletableFuture<ResponseEntity<Object>> uploadPhoneImage(Long phoneId, MultipartFile file) throws ExecutionException, InterruptedException {
        Optional<Phone> phone = phoneRepository.findById(phoneId);
        if (phone.isPresent()) {
            phoneRepository.updatePhoneImage(upload(file).get(), phoneId);
            return CompletableFuture.completedFuture(ResponseHandler.response("File uploaded", HttpStatus.OK, phone));
        }
        return CompletableFuture.completedFuture(ResponseHandler.response("File not uploaded", HttpStatus.EXPECTATION_FAILED, null));
    }

    @Override
    public CompletableFuture<ResponseEntity<Object>> uploadPromotionImage(Long promotionId, MultipartFile file) throws ExecutionException, InterruptedException {
        Optional<Promotion> promotion = promotionRepository.findById(promotionId);
        if (promotion.isPresent()) {
            promotionRepository.updatePromotionImage(upload(file).get(), promotionId);
            return CompletableFuture.completedFuture(ResponseHandler.response("File uploaded", HttpStatus.OK, promotion));
        }
        return CompletableFuture.completedFuture(ResponseHandler.response("File not uploaded", HttpStatus.EXPECTATION_FAILED, null));
    }

    @Override
    public CompletableFuture<ResponseEntity<Object>> uploadStoreImage(Long storeId, MultipartFile file) throws ExecutionException, InterruptedException {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isPresent()) {
            storeRepository.updateStoreImage(upload(file).get(), storeId);
            return CompletableFuture.completedFuture(ResponseHandler.response("File uploaded", HttpStatus.OK, store));
        }
        return CompletableFuture.completedFuture(ResponseHandler.response("File not uploaded", HttpStatus.EXPECTATION_FAILED, null));
    }


    @Override
    public Mono<Resource> getImage(String link) {
        return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(IMAGE_FORMAT, link)));
    }
}
