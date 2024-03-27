package com.ortega.tshomboapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDto {
    private String email;
    private String password;
}
