package com.ortega.tshomboapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private String roleName;
}
