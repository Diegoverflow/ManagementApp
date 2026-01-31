package com.example.BarsAndRestaurantsApp.services;

import com.example.BarsAndRestaurantsApp.domain.entities.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface TicketService {



    TicketEntity save(TicketEntity ticket);

    Page<TicketEntity> findAll(Pageable pageable);

    Optional<TicketEntity> findOne(UUID id);

    boolean exits(UUID id);

    TicketEntity partialUpdate(UUID id, TicketEntity ticket);

    void delete(UUID id);

}
