package com.ortega.tshomboapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
