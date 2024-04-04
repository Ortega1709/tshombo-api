package com.ortega.tshomboapi.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PromotionDto {

    private Long promotionId;
    private String name;
    private String description;
    private String image;
    private String endDate;

}
