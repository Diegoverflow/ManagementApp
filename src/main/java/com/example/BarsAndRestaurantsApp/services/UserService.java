package com.example.BarsAndRestaurantsApp.services;

import com.example.BarsAndRestaurantsApp.domain.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserEntity save(UserEntity user);

    Page<UserEntity> findAll(Pageable pageable);

    Optional<UserEntity> findOne(UUID id);

    boolean exits(UUID id);

    UserEntity partialUpdate(UUID id, UserEntity user);

    void delete(UUID id);

}
