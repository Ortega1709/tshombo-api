package com.ortega.tshomboapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID promotionId;
    private String name;
    private String description;
    private String image;
    private LocalDateTime endDate;

}
