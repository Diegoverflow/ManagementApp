package com.example.BarsAndRestaurantsApp.repositories;

import com.example.BarsAndRestaurantsApp.domain.entities.TicketEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, UUID>,
        ListPagingAndSortingRepository<TicketEntity,UUID> {
}
