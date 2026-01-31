package com.example.BarsAndRestaurantsApp.repositories;

import com.example.BarsAndRestaurantsApp.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID>,
        ListPagingAndSortingRepository<UserEntity,UUID> {
}
