package com.example.BarsAndRestaurantsApp.mappers;

import com.example.BarsAndRestaurantsApp.domain.CreateProductRequest;
import com.example.BarsAndRestaurantsApp.domain.UpdateProductRequest;
import com.example.BarsAndRestaurantsApp.domain.dtos.CreateProductRequestDto;
import com.example.BarsAndRestaurantsApp.domain.dtos.ProductDto;
import com.example.BarsAndRestaurantsApp.domain.dtos.UpdateProductRequestDto;
import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductEntity mapFrom(ProductDto productDto);
    ProductDto toDto(ProductEntity productEntity);

    CreateProductRequest toCreateProduct(CreateProductRequestDto createProductRequestDto);
    UpdateProductRequest toUpdateProduct(UpdateProductRequestDto updateProductRequestDto);
}
