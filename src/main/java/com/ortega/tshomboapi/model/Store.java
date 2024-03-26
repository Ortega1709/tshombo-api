package com.ortega.tshomboapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Entity
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

    @OneToMany(mappedBy = "store")
    private List<Phone> phones;

}
