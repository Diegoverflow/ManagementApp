package com.example.BarsAndRestaurantsApp.mappers.impl;

import com.example.BarsAndRestaurantsApp.domain.dtos.OrderDto;
import com.example.BarsAndRestaurantsApp.domain.entities.CustomerOrderEntity;
import com.example.BarsAndRestaurantsApp.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements Mapper<CustomerOrderEntity, OrderDto> {

    private ModelMapper modelMapper;

    public OrderMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDto mapTo(CustomerOrderEntity customerOrderEntity) {
        return modelMapper.map(customerOrderEntity, OrderDto.class);
    }

    @Override
    public CustomerOrderEntity mapFrom(OrderDto orderDto) {
        return modelMapper.map(orderDto, CustomerOrderEntity.class);
    }
}
