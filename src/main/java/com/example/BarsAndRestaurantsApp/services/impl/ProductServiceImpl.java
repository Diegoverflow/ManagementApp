package com.example.BarsAndRestaurantsApp.services.impl;

import com.example.BarsAndRestaurantsApp.domain.CreateProductRequest;
import com.example.BarsAndRestaurantsApp.domain.UpdateProductRequest;
import com.example.BarsAndRestaurantsApp.domain.dtos.ProductDto;
import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import com.example.BarsAndRestaurantsApp.repositories.ProductRepository;
import com.example.BarsAndRestaurantsApp.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    @Transactional
    public ProductEntity update(UUID uuid, UpdateProductRequest product) {

        var existingProduct = repository.findById(uuid).orElseThrow(() ->
                new EntityNotFoundException("product not found with id: " + uuid));

        if(repository.existsByTitleItIgnoreCaseAndIdNot(product.getTitleIt(), uuid)){
            throw new IllegalArgumentException("It title already exists");
        }
        existingProduct.setTitleIt(product.getTitleIt());

        if(repository.existsByTitleEnIgnoreCaseAndIdNot(product.getTitleEn(), uuid)){
            throw new IllegalArgumentException("En title already exists");
        }
        existingProduct.setTitleEn(product.getTitleEn());

        existingProduct.setDescriptionIt(product.getDescriptionIt());
        existingProduct.setDescriptionEn(product.getDescriptionEn());
        existingProduct.setIngredientsIt(product.getIngredientsIt());
        existingProduct.setIngredientsEn(product.getIngredientsEn());
        existingProduct.setProductCategory(product.getProductCategory());
        existingProduct.setAllergens(product.getAllergens());
        existingProduct.setVeganOk(product.getVeganOk());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setInStock(product.getInStock());
        existingProduct.setImageName(product.getImageName());

        return repository.save(existingProduct);
    }

    @Override
    @Transactional
    public ProductEntity createProduct(CreateProductRequest createProductRequest) {

        ProductEntity product = new ProductEntity();

        if(repository.existsByTitleItIgnoreCase(createProductRequest.getTitleIt())){
            throw new IllegalArgumentException("It title already exists");
        }
        product.setTitleIt(createProductRequest.getTitleIt());

        if(repository.existsByTitleEnIgnoreCase(createProductRequest.getTitleEn())){
            throw new IllegalArgumentException("En title already exists");
        }
        product.setTitleEn(createProductRequest.getTitleEn());

        product.setDescriptionIt(createProductRequest.getDescriptionIt());
        product.setDescriptionEn(createProductRequest.getDescriptionEn());
        product.setIngredientsIt(createProductRequest.getIngredientsIt());
        product.setIngredientsEn(createProductRequest.getIngredientsEn());
        product.setPrice(createProductRequest.getPrice());
        product.setVeganOk(createProductRequest.getVeganOk());
        product.setAllergens(new HashSet<>(createProductRequest.getAllergens()));
        product.setInStock(createProductRequest.getInStock());
        product.setImageName(createProductRequest.getImageName());
        product.setProductCategory(createProductRequest.getProductCategory());

        return repository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductEntity> findByProductCategory(Pageable pageable, ProductCategory category) {
        return repository.findByProductCategory(pageable, category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> findUploadedCategories() {
        return repository.findDistinctProductCategories();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductEntity findOne(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("product not found with id: " + id));
    }

    @Override
    public boolean exists(UUID id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional
    public ProductEntity partialUpdate(UUID id, ProductDto product) {

        var titleIt = Optional.ofNullable(product.getTitleIt());
        if(titleIt.isPresent()){
            if (repository.existsByTitleItIgnoreCaseAndIdNot(titleIt.get(), id)){
                throw new IllegalArgumentException("It title already exists");
            };
        }
        var titleEn = Optional.ofNullable(product.getTitleEn());
        if(titleEn.isPresent()){
            if (repository.existsByTitleItIgnoreCaseAndIdNot(titleEn.get(), id)){
                throw new IllegalArgumentException("It title already exists");
            };
        }

        return repository.findById(id).map(
                existingProduct ->{
                    Optional.ofNullable(product.getProductCategory()).ifPresent(existingProduct::setProductCategory);
                    Optional.ofNullable(product.getDescriptionIt()).ifPresent(existingProduct::setDescriptionIt);
                    Optional.ofNullable(product.getDescriptionEn()).ifPresent(existingProduct::setDescriptionEn);
                    Optional.ofNullable(product.getIngredientsIt()).ifPresent(existingProduct::setIngredientsIt);
                    Optional.ofNullable(product.getIngredientsEn()).ifPresent(existingProduct::setIngredientsEn);
                    titleIt.ifPresent(existingProduct::setTitleIt);
                    titleEn.ifPresent(existingProduct::setTitleEn);
                    Optional.ofNullable(product.getAllergens()).ifPresent(existingProduct::setAllergens);
                    Optional.ofNullable(product.getVeganOk()).ifPresent(existingProduct::setVeganOk);
                    Optional.ofNullable(product.getPrice()).ifPresent(existingProduct::setPrice);
                    Optional.ofNullable(product.getInStock()).ifPresent(existingProduct::setInStock);
                    Optional.ofNullable(product.getImageName()).ifPresent(existingProduct::setImageName);
                    return repository.save(existingProduct);
                }
        ).orElseThrow(() ->  new EntityNotFoundException("product not found with id: " + id));

    }

    @Override
    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
