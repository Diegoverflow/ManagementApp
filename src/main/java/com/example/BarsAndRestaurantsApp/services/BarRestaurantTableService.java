package com.example.BarsAndRestaurantsApp.services;

import com.example.BarsAndRestaurantsApp.domain.entities.BarRestaurantTableEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.CustomerOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface BarRestaurantTableService {

    BarRestaurantTableEntity save(BarRestaurantTableEntity table);

    Page<BarRestaurantTableEntity> findAll(Pageable pageable);

    Optional<BarRestaurantTableEntity> findOne(Integer id);

    boolean exits(Integer id);

    //BarRestaurantTableEntity partialUpdate(Integer id, BarRestaurantTableEntity table);

    void delete(Integer id);


}
