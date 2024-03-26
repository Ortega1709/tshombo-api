package com.ortega.tshomboapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;


@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID locationId;
    private Double lon;
    private Double lat;

}
