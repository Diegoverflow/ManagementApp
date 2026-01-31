package com.example.BarsAndRestaurantsApp.controllers;

import com.example.BarsAndRestaurantsApp.domain.dtos.OrderDto;
import com.example.BarsAndRestaurantsApp.domain.entities.CustomerOrderEntity;
import com.example.BarsAndRestaurantsApp.mappers.Mapper;
import com.example.BarsAndRestaurantsApp.services.CustomerOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class CustomerOrderController {

    private CustomerOrderService service;

    private Mapper<CustomerOrderEntity, OrderDto> mapper;

    public CustomerOrderController(CustomerOrderService service,
                                   Mapper<CustomerOrderEntity, OrderDto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PutMapping(path = "/orders/{uuid}")
    public ResponseEntity<OrderDto> createUpdateProduct(@PathVariable UUID uuid,
                                                          @RequestBody OrderDto product){

        CustomerOrderEntity customerOrderEntity = mapper.mapFrom(product);
        boolean productExists = service.exits(uuid);
        CustomerOrderEntity saved = service.save(customerOrderEntity);
        OrderDto savedDto = mapper.mapTo(saved);

        if (productExists){
            return new ResponseEntity<>(savedDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
        }

    }

    @PatchMapping(path = "/orders/{uuid}")
    public ResponseEntity<OrderDto> partialUpdate(@PathVariable UUID uuid,
                                                    @RequestBody OrderDto product){


        boolean productExists = service.exits(uuid);
        if (!productExists){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CustomerOrderEntity customerOrderEntity = mapper.mapFrom(product);
        CustomerOrderEntity saved = service.partialUpdate(uuid, customerOrderEntity);
        OrderDto savedDto = mapper.mapTo(saved);

        return new ResponseEntity<>(savedDto, HttpStatus.OK);

    }


    @GetMapping(path = "/orders")
    public Page<OrderDto> listProducts(Pageable pageable){
        Page<CustomerOrderEntity> orders = service.findAll(pageable);
        return orders.map(mapper::mapTo);
    }

    @GetMapping(path = "/orders/{uuid}")
    public ResponseEntity<OrderDto> getProduct(@PathVariable UUID uuid){
        Optional<CustomerOrderEntity> foundProduct = service.findOne(uuid);
        return foundProduct.map(customerOrderEntity -> {
            OrderDto OrderDto = mapper.mapTo(customerOrderEntity);
            return new ResponseEntity<>(OrderDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/orders/{uuid}")
    public ResponseEntity deleteProduct(@PathVariable UUID uuid){
        service.delete(uuid);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
}
