package com.example.BarsAndRestaurantsApp.services.impl;

import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import com.example.BarsAndRestaurantsApp.repositories.ProductRepository;
import com.example.BarsAndRestaurantsApp.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        return repository.save(product);
    }

    @Override
    public Page<ProductEntity> findByProductCategory(Pageable pageable, ProductCategory category) {
        return repository.findByProductCategory(pageable, category);
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<ProductEntity> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public boolean exits(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public ProductEntity partialUpdate(UUID id, ProductEntity product) {
        product.setId(id);
        return repository.findById(id).map(
                existingProduct ->{
                    Optional.ofNullable(product.getProductCategory()).ifPresent(existingProduct::setProductCategory);
                    Optional.ofNullable(product.getDescriptionIt()).ifPresent(existingProduct::setDescriptionIt);
                    Optional.ofNullable(product.getDescriptionEn()).ifPresent(existingProduct::setDescriptionEn);
                    Optional.ofNullable(product.getTitle()).ifPresent(existingProduct::setTitle);
                    Optional.ofNullable(product.getPrice()).ifPresent(existingProduct::setPrice);
                    Optional.ofNullable(product.getInStock()).ifPresent(existingProduct::setInStock);
                    Optional.ofNullable(product.getImageName()).ifPresent(existingProduct::setImageName);
                    return repository.save(existingProduct);
                }
        ).orElseThrow(() -> new RuntimeException("Product does not exist"));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
