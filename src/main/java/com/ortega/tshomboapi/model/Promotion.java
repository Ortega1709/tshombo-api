package com.ortega.tshomboapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID promotionId;
    private String name;
    private String description;
    private String image;
    private String endDate;

    @ManyToOne
    @JoinColumn(name="storeId")
    private Store store;

}
