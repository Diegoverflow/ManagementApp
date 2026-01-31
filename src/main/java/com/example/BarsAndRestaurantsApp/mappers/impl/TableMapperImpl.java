package com.example.BarsAndRestaurantsApp.mappers.impl;

import com.example.BarsAndRestaurantsApp.domain.dtos.TableDto;
import com.example.BarsAndRestaurantsApp.domain.entities.BarRestaurantTableEntity;
import com.example.BarsAndRestaurantsApp.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TableMapperImpl implements Mapper<BarRestaurantTableEntity, TableDto> {

    private ModelMapper modelMapper;

    public TableMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TableDto mapTo(BarRestaurantTableEntity barRestaurantTableEntity) {
        return modelMapper.map(barRestaurantTableEntity, TableDto.class);
    }

    @Override
    public BarRestaurantTableEntity mapFrom(TableDto tableDto) {
        return modelMapper.map(tableDto, BarRestaurantTableEntity.class);
    }
}
