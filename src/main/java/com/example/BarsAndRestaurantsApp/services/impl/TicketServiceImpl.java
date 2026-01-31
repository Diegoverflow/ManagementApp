package com.example.BarsAndRestaurantsApp.services.impl;

import com.example.BarsAndRestaurantsApp.domain.entities.TicketEntity;
import com.example.BarsAndRestaurantsApp.repositories.TicketRepository;
import com.example.BarsAndRestaurantsApp.services.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository repository;

    public TicketServiceImpl(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public TicketEntity save(TicketEntity ticket) {
        return repository.save(ticket);
    }

    @Override
    public Page<TicketEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<TicketEntity> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public boolean exits(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public TicketEntity partialUpdate(UUID id, TicketEntity ticket) {
        ticket.setId(id);
        return repository.findById(id).map(
                existingTicket ->{
                    Optional.ofNullable(ticket.getDate()).ifPresent(existingTicket::setDate);
                    Optional.ofNullable(ticket.getTicketTable()).ifPresent(existingTicket::setTicketTable);
                    Optional.ofNullable(ticket.getOrders()).ifPresent(existingTicket::setOrders);
                    return repository.save(existingTicket);
                }
        ).orElseThrow(() -> new RuntimeException("Ticket does not exist"));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
