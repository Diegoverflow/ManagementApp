package com.example.BarsAndRestaurantsApp.controllers;

import com.example.BarsAndRestaurantsApp.domain.dtos.UsersDto;
import com.example.BarsAndRestaurantsApp.domain.entities.UserEntity;
import com.example.BarsAndRestaurantsApp.mappers.Mapper;
import com.example.BarsAndRestaurantsApp.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    private UserService service;

    private Mapper<UserEntity, UsersDto> mapper;

    public UserController(UserService service, Mapper<UserEntity, UsersDto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PutMapping(path = "/users/{uuid}")
    public ResponseEntity<UsersDto> createUpdateUser(@PathVariable UUID uuid,
                                                     @RequestBody UsersDto user){

        UserEntity userEntity = mapper.mapFrom(user);
        boolean userExists = service.exits(uuid);
        UserEntity saved = service.save(userEntity);
        UsersDto savedDto = mapper.mapTo(saved);

        if (userExists){
            return new ResponseEntity<>(savedDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
        }

    }

    @PatchMapping(path = "/users/{uuid}")
    public ResponseEntity<UsersDto> partialUpdate(@PathVariable UUID uuid,
                                                     @RequestBody UsersDto user){


        boolean userExists = service.exits(uuid);
        if (!userExists){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = mapper.mapFrom(user);
        UserEntity saved = service.partialUpdate(uuid, userEntity);
        UsersDto savedDto = mapper.mapTo(saved);

        return new ResponseEntity<>(savedDto, HttpStatus.OK);

    }


    @GetMapping(path = "/users")
    public Page<UsersDto> listUsers(Pageable pageable){
        Page<UserEntity> users = service.findAll(pageable);
        return users.map(mapper::mapTo);
    }

    @GetMapping(path = "/users/{uuid}")
    public ResponseEntity<UsersDto> getUser(@PathVariable UUID uuid){
        Optional<UserEntity> foundUser = service.findOne(uuid);
        return foundUser.map(userEntity -> {
            UsersDto userDto = mapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/users/{uuid}")
    public ResponseEntity deleteUser(@PathVariable UUID uuid){
        service.delete(uuid);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




}
