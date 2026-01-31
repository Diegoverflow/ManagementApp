package com.example.BarsAndRestaurantsApp.controllers;

import com.example.BarsAndRestaurantsApp.domain.dtos.TableDto;
import com.example.BarsAndRestaurantsApp.domain.entities.BarRestaurantTableEntity;
import com.example.BarsAndRestaurantsApp.mappers.Mapper;
import com.example.BarsAndRestaurantsApp.services.BarRestaurantTableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BarRestaurantTableController {

    private BarRestaurantTableService service;

    private Mapper<BarRestaurantTableEntity, TableDto> mapper;

    public BarRestaurantTableController(BarRestaurantTableService service,
                                        Mapper<BarRestaurantTableEntity, TableDto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PutMapping(path = "/tables/{id}")
    public ResponseEntity<TableDto> createUpdateTable(@PathVariable int id,
                                                      @RequestBody TableDto table){

        BarRestaurantTableEntity tableEntity = mapper.mapFrom(table);
        boolean tableExists = service.exits(id);
        BarRestaurantTableEntity saved = service.save(tableEntity);
        TableDto savedDto = mapper.mapTo(saved);

        if (tableExists){
            return new ResponseEntity<>(savedDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
        }

    }

    /*
    @PatchMapping(path = "/tables/{id}")
    public ResponseEntity<TableDto> partialUpdate(@PathVariable int id,
                                                    @RequestBody TableDto table){


        boolean tableExists = service.exits(id);
        if (!tableExists){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BarRestaurantTableEntity tableEntity = mapper.mapFrom(table);
        BarRestaurantTableEntity saved = service.partialUpdate(id, tableEntity);
        TableDto savedDto = mapper.mapTo(saved);

        return new ResponseEntity<>(savedDto, HttpStatus.OK);

    }
    */

    @GetMapping(path = "/tables")
    public Page<TableDto> listTables(Pageable pageable){
        Page<BarRestaurantTableEntity> tables = service.findAll(pageable);
        return tables.map(mapper::mapTo);
    }

    @GetMapping(path = "/tables/{id}")
    public ResponseEntity<TableDto> getTable(@PathVariable int id){
        Optional<BarRestaurantTableEntity> foundTable = service.findOne(id);
        return foundTable.map(tableEntity -> {
            TableDto tableDto = mapper.mapTo(tableEntity);
            return new ResponseEntity<>(tableDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/tables/{id}")
    public ResponseEntity deleteTable(@PathVariable int id){
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
}
