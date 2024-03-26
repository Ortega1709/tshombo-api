package com.ortega.tshomboapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID phoneId;
    private String brand;
    private String description;
    private Double price;
    private String image;

    @ManyToOne
    private Store store;


}
