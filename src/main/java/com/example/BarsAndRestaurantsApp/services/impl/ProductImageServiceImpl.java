package com.example.BarsAndRestaurantsApp.services.impl;

import com.example.BarsAndRestaurantsApp.errors.ResourceNotFoundException;
import com.example.BarsAndRestaurantsApp.services.ProductImageService;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Getter
@Service
public class ProductImageServiceImpl implements ProductImageService {

    private final String uploadDir =
            "C:\\Users\\belar\\Documents\\BarsAndRestaurantsApp\\BarsAndRestaurantsApp\\src\\main\\resources\\static\\images\\products";

    @Override
    public String save(MultipartFile image) {

        try {

            //TODO: sanity check

            String contentType = image.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new IllegalArgumentException("Invalid file type");
            }

            String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
            if (extension == null || extension.isBlank()) {
                throw new IllegalArgumentException("Invalid file extension");
            }

            String imageName = UUID.randomUUID() + "." + extension;

            Path dir = Paths.get(uploadDir);
            Path imagePath = dir.resolve(imageName);

            Files.createDirectories(dir);
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            return imageName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }

    @Override
    public Resource load(String imageName) {

        try {
            Path imagePath = Paths.get(uploadDir).resolve(imageName);
            if (!Files.exists(imagePath)) {
                throw new ResourceNotFoundException("Image not found");
            }
            return new UrlResource(imagePath.toUri());

        } catch (IOException e) {
            throw new RuntimeException("Failed to load image", e);
        }

    }

    @Override
    public void delete(String imageName) {
        try {
            Files.deleteIfExists(Paths.get(uploadDir).resolve(imageName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image", e);
        }
    }

}
