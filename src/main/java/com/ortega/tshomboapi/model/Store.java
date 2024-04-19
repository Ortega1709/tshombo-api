package com.ortega.tshomboapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;
    private String name;
    private String city;
    private String avenue;
    private String commune;
    private String rccm;
    private String image;

    @OneToOne
    @JoinColumn(referencedColumnName = "userId")
    private User user;

    @OneToOne
    @JoinColumn(referencedColumnName = "locationId")
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Phone> phones;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Promotion> promotions;

}
