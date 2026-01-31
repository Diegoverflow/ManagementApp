package com.example.BarsAndRestaurantsApp.controllers;

import com.example.BarsAndRestaurantsApp.domain.dtos.TicketDto;
import com.example.BarsAndRestaurantsApp.domain.entities.TicketEntity;
import com.example.BarsAndRestaurantsApp.mappers.Mapper;
import com.example.BarsAndRestaurantsApp.services.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class TicketController {

    private TicketService service;

    private Mapper<TicketEntity, TicketDto> mapper;

    public TicketController(TicketService service, Mapper<TicketEntity, TicketDto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PutMapping(path = "/tickets/{uuid}")
    public ResponseEntity<TicketDto> createUpdateTicket(@PathVariable UUID uuid,
                                                        @RequestBody TicketDto ticket){

        TicketEntity ticketEntity = mapper.mapFrom(ticket);
        boolean ticketExists = service.exits(uuid);
        TicketEntity saved = service.save(ticketEntity);
        TicketDto savedDto = mapper.mapTo(saved);

        if (ticketExists){
            return new ResponseEntity<>(savedDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
        }

    }

    @PatchMapping(path = "/tickets/{uuid}")
    public ResponseEntity<TicketDto> partialUpdate(@PathVariable UUID uuid,
                                                  @RequestBody TicketDto ticket){


        boolean ticketExists = service.exits(uuid);
        if (!ticketExists){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TicketEntity ticketEntity = mapper.mapFrom(ticket);
        TicketEntity saved = service.partialUpdate(uuid, ticketEntity);
        TicketDto savedDto = mapper.mapTo(saved);

        return new ResponseEntity<>(savedDto, HttpStatus.OK);

    }


    @GetMapping(path = "/tickets")
    public Page<TicketDto> listTickets(Pageable pageable){
        Page<TicketEntity> tickets = service.findAll(pageable);
        return tickets.map(mapper::mapTo);
    }

    @GetMapping(path = "/tickets/{uuid}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable UUID uuid){
        Optional<TicketEntity> foundTicket = service.findOne(uuid);
        return foundTicket.map(ticketEntity -> {
            TicketDto ticketDto = mapper.mapTo(ticketEntity);
            return new ResponseEntity<>(ticketDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/tickets/{uuid}")
    public ResponseEntity deleteTicket(@PathVariable UUID uuid){
        service.delete(uuid);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
