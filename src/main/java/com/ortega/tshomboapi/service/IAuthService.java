package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.dto.AuthDto;
import com.ortega.tshomboapi.dto.RegisterDto;
import com.ortega.tshomboapi.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IAuthService {

    public ResponseEntity<Object> authentication(AuthDto authDto);
    public ResponseEntity<Object> register(RegisterDto registerDto);

}
