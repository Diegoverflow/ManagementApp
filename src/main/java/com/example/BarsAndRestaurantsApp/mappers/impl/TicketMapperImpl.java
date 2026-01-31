package com.example.BarsAndRestaurantsApp.mappers.impl;

import com.example.BarsAndRestaurantsApp.domain.dtos.TicketDto;
import com.example.BarsAndRestaurantsApp.domain.entities.TicketEntity;
import com.example.BarsAndRestaurantsApp.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapperImpl implements Mapper<TicketEntity, TicketDto> {

    private ModelMapper modelMapper;

    public TicketMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TicketDto mapTo(TicketEntity ticketEntity) {
        return modelMapper.map(ticketEntity, TicketDto.class);
    }

    @Override
    public TicketEntity mapFrom(TicketDto ticketDto) {
        return modelMapper.map(ticketDto, TicketEntity.class);
    }
}
