package com.example.BarsAndRestaurantsApp.mappers.impl;

import com.example.BarsAndRestaurantsApp.domain.dtos.ProductDto;
import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import com.example.BarsAndRestaurantsApp.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImplOld implements Mapper<ProductEntity, ProductDto> {

    private ModelMapper modelMapper;

    public ProductMapperImplOld(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto mapTo(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDto.class);
    }

    @Override
    public ProductEntity mapFrom(ProductDto productDto) {
        return modelMapper.map(productDto, ProductEntity.class);
    }
}
