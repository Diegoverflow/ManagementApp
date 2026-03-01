package com.example.BarsAndRestaurantsApp.services;

import com.example.BarsAndRestaurantsApp.domain.CreateProductRequest;
import com.example.BarsAndRestaurantsApp.domain.UpdateProductRequest;
import com.example.BarsAndRestaurantsApp.domain.dtos.ProductDto;
import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    ProductEntity update(UUID uuid, UpdateProductRequest product);

    ProductEntity createProduct(CreateProductRequest createProductRequestDto);

    Page<ProductEntity> findAll(Pageable pageable);

    Page<ProductEntity> findByProductCategory(Pageable pageable, ProductCategory category);

    List<ProductCategory> findUploadedCategories();

    ProductEntity findOne(UUID id);

    boolean exits(UUID id);

    ProductEntity partialUpdate(UUID id, ProductDto product);

    void delete(UUID id);

}
