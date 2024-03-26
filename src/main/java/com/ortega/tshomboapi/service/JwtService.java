package com.ortega.tshomboapi.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String generateToken(UserDetails userDetails);
    Boolean isValidToken(String token, UserDetails userDetails);

}
