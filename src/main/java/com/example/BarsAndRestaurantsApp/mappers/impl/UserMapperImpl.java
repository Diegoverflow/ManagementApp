package com.example.BarsAndRestaurantsApp.mappers.impl;

import com.example.BarsAndRestaurantsApp.domain.dtos.UsersDto;
import com.example.BarsAndRestaurantsApp.domain.entities.UserEntity;
import com.example.BarsAndRestaurantsApp.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UsersDto> {

    private ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UsersDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UsersDto.class);
    }

    @Override
    public UserEntity mapFrom(UsersDto usersDto) {
        return modelMapper.map(usersDto, UserEntity.class);
    }

}
