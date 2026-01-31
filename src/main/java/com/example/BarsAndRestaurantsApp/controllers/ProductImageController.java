package com.example.BarsAndRestaurantsApp.controllers;

import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import com.example.BarsAndRestaurantsApp.services.ProductImageService;
import com.example.BarsAndRestaurantsApp.services.ProductService;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class ProductImageController {

    private ProductImageService imageService;
    private ProductService productService;

    public ProductImageController(ProductImageService imageService,
                                  ProductService productService) {
        this.imageService = imageService;
        this.productService = productService;
    }

    @PostMapping("/product-image/upload")
    public ResponseEntity<?> upload(
            @RequestParam UUID id,
            @RequestParam MultipartFile image) throws Exception {

        String imageName = imageService.save(image);

        ProductEntity product = productService.findOne(id)
                .orElseThrow(() -> new RuntimeException("Product does not exist"));

        product.setImageName(imageName);
        productService.save(product);

        return ResponseEntity.ok(
                Map.of("imageName", imageName)
        );

    }

    @GetMapping("/product-image/{imageName}")
    public ResponseEntity<Resource> serve(@PathVariable String imageName)
            throws IOException {

        Resource image = imageService.load(imageName);

        Path imagePath = Paths.get(imageService.getUploadDir()).resolve(imageName);

        if (!Files.exists(imagePath)) {
            throw new FileNotFoundException("Image not found");
        }

        String contentType = Files.probeContentType(imagePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
                .body(image);
    }

}
