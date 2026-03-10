package com.example.BarsAndRestaurantsApp.controllers;

import com.example.BarsAndRestaurantsApp.domain.CreateProductRequest;
import com.example.BarsAndRestaurantsApp.domain.UpdateProductRequest;
import com.example.BarsAndRestaurantsApp.domain.dtos.CreateProductRequestDto;
import com.example.BarsAndRestaurantsApp.domain.dtos.ProductDto;
import com.example.BarsAndRestaurantsApp.domain.dtos.UpdateProductRequestDto;
import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import com.example.BarsAndRestaurantsApp.mappers.ProductMapper;
import com.example.BarsAndRestaurantsApp.services.ProductImageService;
import com.example.BarsAndRestaurantsApp.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(path = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductImageService productImageService;
    //private final Mapper<ProductEntity, ProductDto> mapper;
    private final ProductMapper mapper;

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable UUID uuid,
                                                    @Valid @RequestBody UpdateProductRequestDto product){

        UpdateProductRequest productEntity = mapper.toUpdateProduct(product);
        ProductEntity updatedProduct = service.update(uuid, productEntity);
        ProductDto dto = mapper.toDto(updatedProduct);
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody CreateProductRequestDto product){

        CreateProductRequest productEntity = mapper.toCreateProduct(product);
        ProductEntity saved = service.createProduct(productEntity);
        ProductDto savedDto = mapper.toDto(saved);
        return new ResponseEntity<>(
                savedDto,
                HttpStatus.CREATED
        );

    }


    @PatchMapping(path = "/{uuid}")
    public ResponseEntity<ProductDto> partialUpdate(@PathVariable UUID uuid,
                                                    @RequestBody ProductDto product){

        ProductEntity saved = service.partialUpdate(uuid, product);
        ProductDto savedDto = mapper.toDto(saved);
        return ResponseEntity.ok(savedDto);

    }

    @GetMapping
    public Page<ProductDto> listProductsByCategory(Pageable pageable,
                                                   @RequestParam(required = false) ProductCategory category){
        Page<ProductEntity> products = (category == null) ?
                                        service.findAll(pageable)
                                        : service.findByProductCategory(pageable, category);
        return products.map(mapper::toDto);
    }

    @GetMapping(path = "/categories")
    public List<ProductCategory> listUploadedCategories(){
        return service.findUploadedCategories();
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable UUID uuid){
        ProductEntity foundProduct = service.findOne(uuid);
        return ResponseEntity.ok(mapper.toDto(foundProduct));
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID uuid){
        ProductEntity productEntity =  service.findOne(uuid);
        productImageService.delete(productEntity.getImageName());
        service.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
