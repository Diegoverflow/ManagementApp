package com.example.BarsAndRestaurantsApp.controllers;

import com.example.BarsAndRestaurantsApp.domain.dtos.ProductDto;
import com.example.BarsAndRestaurantsApp.errors.ResourceNotFoundException;
import com.example.BarsAndRestaurantsApp.services.ProductImageService;
import com.example.BarsAndRestaurantsApp.services.ProductService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/product-images")
public class ProductImageController {

    private final ProductImageService imageService;
    private final ProductService productService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(
            @RequestParam UUID id,
            @RequestParam MultipartFile image) {

        if (image.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }

        if (!productService.exists(id)) {
            throw new ResourceNotFoundException("Product not found");
        }

        String imageName = imageService.save(image);

        productService.partialUpdate(
                id,
                ProductDto.builder().imageName(imageName).build()
        );

        return ResponseEntity.ok(Map.of("imageName", imageName));
    }

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> serve(@PathVariable String imageName) throws IOException {

        Resource image = imageService.load(imageName);

        String contentType = Files.probeContentType(
                Paths.get(imageService.getUploadDir()).resolve(imageName)
        );

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
                .body(image);

    }
}