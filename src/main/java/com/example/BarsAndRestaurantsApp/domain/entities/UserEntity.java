package com.example.BarsAndRestaurantsApp.domain.entities;

import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
