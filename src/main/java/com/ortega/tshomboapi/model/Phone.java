package com.ortega.tshomboapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;
    private String brand;
    private String description;
    private Double price;
    private String image;

    @ManyToOne
    @JoinColumn(referencedColumnName = "storeId")
    private Store store;

}
