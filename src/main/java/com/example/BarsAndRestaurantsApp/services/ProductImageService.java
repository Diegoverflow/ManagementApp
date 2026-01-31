package com.example.BarsAndRestaurantsApp.services;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ProductImageService {

    String save(MultipartFile file) throws IOException;

    Resource load(String filename) throws IOException;

    void delete(String filename) throws IOException;

    String getUploadDir();

}
