package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.dto.PhoneDto;
import com.ortega.tshomboapi.dto.PromotionDto;
import org.springframework.http.ResponseEntity;

public interface IPhoneService {

    public ResponseEntity<Object> getAllPhones();
    public ResponseEntity<Object> getPhoneById(Long id);
    public ResponseEntity<Object> getPhoneByStoreId(Long id);
    public ResponseEntity<Object> savePhone(Long storeId, PhoneDto phoneDto);
    public ResponseEntity<Object> updatePhone(Long userId, PhoneDto phoneDto);
    public ResponseEntity<Object> deleteByPhoneId(Long id);

}
