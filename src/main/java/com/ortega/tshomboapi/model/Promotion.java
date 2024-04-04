package com.ortega.tshomboapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;
    private String name;
    private String description;
    private String image;
    private String endDate;

    @ManyToOne
    @JoinColumn(name="storeId")
    private Store store;

}
