package com.example.BarsAndRestaurantsApp.controllers;

import com.example.BarsAndRestaurantsApp.domain.dtos.ProductDto;
import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import com.example.BarsAndRestaurantsApp.mappers.Mapper;
import com.example.BarsAndRestaurantsApp.services.ProductImageService;
import com.example.BarsAndRestaurantsApp.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

    private ProductService service;
    private ProductImageService productImageService;

    private Mapper<ProductEntity, ProductDto> mapper;

    public ProductController(ProductService service,
                             ProductImageService productImageService,
                             Mapper<ProductEntity, ProductDto> mapper) {
        this.service = service;
        this.productImageService = productImageService;
        this.mapper = mapper;
    }

    @PutMapping(path = "/products/{uuid}")
    public ResponseEntity<ProductDto> createUpdateProduct(@PathVariable UUID uuid,
                                                          @RequestBody ProductDto product){

        ProductEntity productEntity = mapper.mapFrom(product);
        boolean productExists = service.exits(uuid);
        ProductEntity saved = service.save(productEntity);
        ProductDto savedDto = mapper.mapTo(saved);

        if (productExists){
            return new ResponseEntity<>(savedDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
        }

    }

    @PatchMapping(path = "/products/{uuid}")
    public ResponseEntity<ProductDto> partialUpdate(@PathVariable UUID uuid,
                                                   @RequestBody ProductDto product){


        boolean productExists = service.exits(uuid);
        if (!productExists){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductEntity productEntity = mapper.mapFrom(product);
        ProductEntity saved = service.partialUpdate(uuid, productEntity);
        ProductDto savedDto = mapper.mapTo(saved);

        return new ResponseEntity<>(savedDto, HttpStatus.OK);

    }

    /*
    @GetMapping(path = "/products")
    public Page<ProductDto> listProducts(Pageable pageable){
        Page<ProductEntity> products = service.findAll(pageable);
        return products.map(mapper::mapTo);
    }
    */

    @GetMapping(path = "/products")
    public Page<ProductDto> listProductsByCategory(Pageable pageable,
                                                   @RequestParam(required = false) ProductCategory category){
        Page<ProductEntity> products = (category == null) ?
                                        service.findAll(pageable)
                                        : service.findByProductCategory(pageable, category);
        return products.map(mapper::mapTo);
    }

    @GetMapping(path = "/products/categories")
    public List<ProductCategory> listUploadedCategories(){
        return service.findUploadedCategories();
    }

    @GetMapping(path = "/products/{uuid}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable UUID uuid){
        Optional<ProductEntity> foundProduct = service.findOne(uuid);
        return foundProduct.map(productEntity -> {
            ProductDto ProductDto = mapper.mapTo(productEntity);
            return new ResponseEntity<>(ProductDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/products/{uuid}")
    public ResponseEntity deleteProduct(@PathVariable UUID uuid){
        service.findOne(uuid).ifPresent((productEntity -> {
            try {
                productImageService.delete(productEntity.getImageName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        service.delete(uuid);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
