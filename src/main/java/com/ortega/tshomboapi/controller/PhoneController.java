package com.ortega.tshomboapi.controller;


import com.ortega.tshomboapi.dto.PhoneDto;
import com.ortega.tshomboapi.service.IPhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PhoneController {

    private final IPhoneService phoneService;

    @GetMapping("/phones")
    public ResponseEntity<Object> getAllPhones() {
        return phoneService.getAllPhones();
    }

    @GetMapping("/{storeId}/phones")
    public ResponseEntity<Object> getPhoneByStoreId(@PathVariable("storeId") Long storeId) {
        return phoneService.getPhoneByStoreId(storeId);
    }

    @PostMapping("/{storeId}/phones")
    public ResponseEntity<Object> savePhone(@PathVariable("storeId") Long storeId, @RequestBody PhoneDto phoneDto) {
        return phoneService.savePhone(storeId, phoneDto);
    }

    @PutMapping("/{storeId}/phones")
    public ResponseEntity<Object> updatePhone(@PathVariable("storeId") Long storeId, @RequestBody PhoneDto phoneDto) {
        return phoneService.updatePhone(storeId, phoneDto);
    }

    @DeleteMapping("/phones/{id}")
    public ResponseEntity<Object> deletePhoneById(@PathVariable("id") Long id) {
        return phoneService.deleteByPhoneId(id);
    }

    @GetMapping("/phones/{phoneId}")
    public ResponseEntity<Object> getPhoneById(@PathVariable("phoneId") Long id) {
        return phoneService.getPhoneById(id);
    }
}
