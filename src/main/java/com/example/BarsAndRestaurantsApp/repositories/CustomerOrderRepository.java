package com.example.BarsAndRestaurantsApp.repositories;

import com.example.BarsAndRestaurantsApp.domain.entities.CustomerOrderEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerOrderRepository extends CrudRepository<CustomerOrderEntity, UUID>,
        ListPagingAndSortingRepository<CustomerOrderEntity,UUID> {
}
