package com.example.BarsAndRestaurantsApp.services;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ProductImageService {

    String save(MultipartFile file);

    Resource load(String filename);

    void delete(String filename);

    String getUploadDir();

}
