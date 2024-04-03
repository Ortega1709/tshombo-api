package com.ortega.tshomboapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID storeId;
    private String name;
    private String city;
    private String avenue;
    private String commune;
    private String rccm;
    private String image;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "locationId")
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Phone> phones;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Promotion> promotions;

}
