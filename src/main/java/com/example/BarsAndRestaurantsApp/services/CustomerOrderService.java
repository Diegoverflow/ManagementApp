package com.example.BarsAndRestaurantsApp.services;

import com.example.BarsAndRestaurantsApp.domain.entities.CustomerOrderEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CustomerOrderService {

    CustomerOrderEntity save(CustomerOrderEntity order);

    Page<CustomerOrderEntity> findAll(Pageable pageable);

    Optional<CustomerOrderEntity> findOne(UUID id);

    boolean exits(UUID id);

    CustomerOrderEntity partialUpdate(UUID id, CustomerOrderEntity order);

    void delete(UUID id);

}
