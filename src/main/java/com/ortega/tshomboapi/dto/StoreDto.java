package com.ortega.tshomboapi.dto;

import com.ortega.tshomboapi.model.Location;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreDto {

    private String name;
    private String city;
    private String avenue;
    private String commune;
    private String rccm;
    private Location location;

}
