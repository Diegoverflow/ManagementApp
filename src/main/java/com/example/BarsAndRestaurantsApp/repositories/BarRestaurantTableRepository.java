package com.example.BarsAndRestaurantsApp.repositories;

import com.example.BarsAndRestaurantsApp.domain.entities.BarRestaurantTableEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BarRestaurantTableRepository extends CrudRepository<BarRestaurantTableEntity, Integer>,
        ListPagingAndSortingRepository<BarRestaurantTableEntity,Integer> {
}
