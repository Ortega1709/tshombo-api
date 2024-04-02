package com.ortega.tshomboapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> response(String message, HttpStatus status, Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("data", object);

        return new ResponseEntity<>(map, status);
    }
}
