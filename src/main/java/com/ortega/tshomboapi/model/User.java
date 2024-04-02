package com.ortega.tshomboapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String username;
    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Role role;

}
