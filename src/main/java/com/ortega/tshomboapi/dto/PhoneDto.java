package com.ortega.tshomboapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDto {

    private Long phoneId;
    private String brand;
    private String description;
    private Double price;
    private String image;

}
