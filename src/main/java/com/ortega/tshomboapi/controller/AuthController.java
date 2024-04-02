package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.dto.AuthDto;
import com.ortega.tshomboapi.dto.RegisterDto;
import com.ortega.tshomboapi.service.IAuthService;
import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final IAuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody AuthDto authDto) {
        try {
            return authService.authentication(authDto);
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody RegisterDto registerDto) {
        try {
            return authService.register(registerDto);
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

}
