package com.example.BarsAndRestaurantsApp.domain.dtos;

import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDto {

    private UUID id;

    private String username;

    private String password;

    private Role role;

}
